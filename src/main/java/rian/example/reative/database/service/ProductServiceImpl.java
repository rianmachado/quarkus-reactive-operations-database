package rian.example.reative.database.service;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import rian.example.reative.database.entity.ProductEntity;
import rian.example.reative.database.mapper.ProductMapper;
import rian.example.reative.database.model.ProductModel;
import rian.example.reative.database.repository.ProductRepository;

@Dependent
public class ProductServiceImpl implements ProductService {

	@Inject
	ProductRepository productRepository;

	@Inject
	ProductMapper productMapper;

	@Override
	public Multi<ProductModel> findAll() {
		return productRepository.findAll().map(entity -> productMapper.toModel(entity));
	}

	@Override
	public Uni<ProductModel> findById(Long id) {
		return productRepository.findById(id).map(entity -> productMapper.toModel(entity));
	}

	@Override
	public Uni<Void> save(ProductModel productModel) {
		return productRepository.save(productMapper.toEntity(productModel));
	}

	@Override
	public Uni<ProductModel> update(ProductModel productModel) {
		return productRepository.update(productMapper.toEntity(productModel))
				.map(entity -> productMapper.toModel(entity));
	}

	@Override
	public Uni<ProductModel> delete(Long id) {
		return productRepository.delete(ProductEntity.builder().id(id).build())
				.map(entity -> productMapper.toModel(entity));
	}

}
