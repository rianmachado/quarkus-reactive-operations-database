package rian.example.reative.database.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import rian.example.reative.database.model.ProductModel;

public interface ProductService {

	Multi<ProductModel> findAll();

	Uni<ProductModel> findById(Long id);

	public Uni<Void> save(ProductModel productModel);

	public Uni<ProductModel> update(ProductModel productModel);

	public Uni<ProductModel> delete(Long i);

}
