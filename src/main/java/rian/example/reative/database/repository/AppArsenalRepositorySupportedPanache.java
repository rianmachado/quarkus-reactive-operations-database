package rian.example.reative.database.repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Multi;
import rian.example.reative.database.entity.AppArsenalEntity;

@ApplicationScoped
public class AppArsenalRepositorySupportedPanache implements PanacheRepository<AppArsenalEntity> {

	public Multi<AppArsenalEntity> findAll(int page, int size) {
		return this.findAll(Sort.ascending("id")).page(page, size).stream();
	}

}
