package rian.example.reative.database.mapper;

import javax.enterprise.context.Dependent;

import rian.example.reative.database.entity.ProductEntity;
import rian.example.reative.database.model.ProductModel;

@Dependent
public class ProductMapper {
	public ProductModel toModel(ProductEntity productEntity) {
		return ProductModel.builder().id(productEntity.id).description(productEntity.description).build();
	}

	public ProductEntity toEntity(ProductModel productModel) {
		return ProductEntity.builder().description(productModel.description).build();
	}
}
