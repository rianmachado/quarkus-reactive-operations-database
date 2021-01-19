/*
 * Copyright 2019 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package rian.example.reative.database.controller;

import java.util.function.Function;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import rian.example.reative.database.model.ProductModel;
import rian.example.reative.database.service.ProductService;

@RequestScoped
@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

	@Inject
	ProductService productServiceImpl;

	@GET
	public Multi<ProductModel> get() {
		return productServiceImpl.findAll();
	}

	@GET
	@Path("{id}")
	public Uni<ProductModel> get(@PathParam Long id) {
		return productServiceImpl.findById(id);
	}

	@POST
	public Uni<Response> create(ProductModel product) {
		if (product == null || product.getId() != null) {
			throw new WebApplicationException("Invalidly request.", HttpStatus.SC_BAD_REQUEST);
		}
		return productServiceImpl.save(product)
				.map(ignore -> Response.ok(product).status(HttpStatus.SC_CREATED).build());
	}

	@PUT
	@Path("{id}")
	public Uni<Response> update(@PathParam Long id, ProductModel productModel) {

		Function<ProductModel, Uni<? extends Response>> update = entity -> {
			return productServiceImpl.update(productModel).onItem()
					.transform(ignore -> Response.ok(productModel).build());
		};

		return productServiceImpl.findById(id).onItem().ifNotNull().transformToUni(update).onItem().ifNull()
				.continueWith(Response.ok().status(HttpStatus.SC_NOT_FOUND).build());

	}

	@DELETE
	@Path("{id}")
	public Uni<ProductModel> delete(@PathParam Long id) {
		return null;
	}
}
