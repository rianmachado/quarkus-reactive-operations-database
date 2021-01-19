package rian.example.reative.database.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.reactive.mutiny.Mutiny;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import rian.example.reative.database.entity.ProductEntity;
import rian.example.reative.database.mapper.ProductMapper;

@ApplicationScoped
public class ProductRepository {

	@Inject
	Mutiny.Session mutinySession;

	@Inject
	ProductMapper productMapper;

	public Multi<ProductEntity> findAll() {
		return mutinySession.createNamedQuery("Product.findAll", ProductEntity.class).getResults();
	}

	public Uni<ProductEntity> findById(Long id) {
		return mutinySession.find(ProductEntity.class, id);
	}

	public Uni<Void> save(ProductEntity productEntity) {
		return mutinySession.persist(productEntity).chain(mutinySession::flush);
	}

	public Uni<Void> update(ProductEntity productEntity) {
		return mutinySession.merge(productEntity).chain(mutinySession::flush);
	}

}
