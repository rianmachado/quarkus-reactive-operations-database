package rian.example.reative.database.repository;

import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.reactive.mutiny.Mutiny;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import rian.example.reative.database.entity.AppArsenalEntity;

@ApplicationScoped
public class AppArsenalRepository {

	@Inject
	Mutiny.Session mutinySession;

	public Multi<AppArsenalEntity> findAll() {
		return mutinySession.createNamedQuery("Arsenal.findAll", AppArsenalEntity.class).getResults();
	}

	public Uni<AppArsenalEntity> findById(Long id) {
		return mutinySession.find(AppArsenalEntity.class, id);
	}

	public Uni<AppArsenalEntity> save(AppArsenalEntity appArsenalEntity) {
		return mutinySession.persist(appArsenalEntity).chain(mutinySession::flush).map(entity -> appArsenalEntity);
	}

	public Uni<AppArsenalEntity> update(AppArsenalEntity productEntity) {
		Function<AppArsenalEntity, Uni<? extends AppArsenalEntity>> update = entity -> {
			entity.setOtherInfo(productEntity.getOtherInfo());
			return mutinySession.flush().onItem().transform(ignore -> productEntity);
		};

		return mutinySession.find(AppArsenalEntity.class, productEntity.getId()).onItem().ifNotNull()
				.transformToUni(update);

	}

	public Uni<AppArsenalEntity> delete(AppArsenalEntity productEntity) {
		Function<AppArsenalEntity, Uni<? extends AppArsenalEntity>> delete = entity -> mutinySession.remove(entity)
				.chain(mutinySession::flush).onItem().transform(ignore -> productEntity);
		return mutinySession.find(AppArsenalEntity.class, productEntity.getId()).onItem().ifNotNull()
				.transformToUni(delete).onItem().ifNull().continueWith(AppArsenalEntity.builder().build());

	}

}
