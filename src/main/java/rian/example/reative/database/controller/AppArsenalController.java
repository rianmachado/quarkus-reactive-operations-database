package rian.example.reative.database.controller;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

import java.util.function.Function;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpStatus;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import rian.example.reative.database.model.AppArsenalRequestModel;
import rian.example.reative.database.model.AppArsenalResponseModel;
import rian.example.reative.database.model.PageAppArsenalResponseModel;
import rian.example.reative.database.service.AppArsenalService;
import rian.example.reative.database.service.AppArsenalSupportPage;


@RequestScoped
@Path("/api/v1/apparsenal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppArsenalController extends AppArsenalSupportPage {

	private static final Logger logger = Logger.getLogger(AppArsenalController.class.getName());

	@Inject
	AppArsenalService appArsenalService;

	@POST
	public Uni<Response> create(@Valid AppArsenalRequestModel productModelRequest) {
		logger.info("Sucessfuly created AppArsenal");
		return appArsenalService.save(productModelRequest)
				.map(responseModel -> Response.ok(responseModel).status(HttpStatus.SC_CREATED).build());
	}

	@GET
	@Path("{id}")
	public Uni<Response> get(@PathParam("id") final Long id) {
		return appArsenalService.findById(id).map(data -> {
			if (data.getId() == null) {
				return null;
			}
			return ok(data).build();
		}).onItem().ifNull().continueWith(status(Status.NOT_FOUND).build());
	}

	@GET
	public Uni<PageAppArsenalResponseModel> get(@QueryParam(value = "page") @DefaultValue("0") int page,
			@QueryParam(value = "size") @DefaultValue("10") int size) {

		PageAppArsenalResponseModel pageAppArsenalResponseModel = PageAppArsenalResponseModel.builder()
				.pageable(this.buidPage(page, size)).build();

		Function<Long, Uni<? extends PageAppArsenalResponseModel>> callPagination = total -> {

			pageAppArsenalResponseModel.setTotalElements(total);
			pageAppArsenalResponseModel.setTotalPages(this.getTotalPage(size, total));
			
			Multi<AppArsenalResponseModel> retorno = appArsenalService.findAll(page, size, total);
			 retorno.subscribe().with(iten -> {
				 pageAppArsenalResponseModel.addContentItem(iten);
			});
			 
			 return retorno.toUni().replaceWith(pageAppArsenalResponseModel);
		};

		return appArsenalService.count().onItem().transformToUni(callPagination);


	}

	@PUT
	@Path("{id}")
	public Uni<Response> update(@PathParam("id") final Long id, AppArsenalRequestModel productModelRequest) {
		return appArsenalService.update(productModelRequest, id).map(model -> {
			if (model.getId() == null) {
				return null;
			}
			return Response.ok(model).status(HttpStatus.SC_OK).build();
		}).onItem().ifNull().continueWith(status(Status.NOT_FOUND).build());

	}

	@DELETE
	@Path("{id}")
	public Uni<Response> delete(@PathParam("id") final Long id) {
		return appArsenalService.delete(id).map(model -> Response.ok(model).status(HttpStatus.SC_NO_CONTENT).build());
	}
}
