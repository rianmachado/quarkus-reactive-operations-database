package rian.example.reative.database.service;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import rian.example.reative.database.entity.AppArsenalEntity;
import rian.example.reative.database.mapper.AppArsenalMapper;
import rian.example.reative.database.model.AppArsenalRequestModel;
import rian.example.reative.database.model.AppArsenalResponseModel;
import rian.example.reative.database.repository.AppArsenalRepository;
import rian.example.reative.database.repository.AppArsenalRepositorySupportedPanache;

@Dependent
public class AppArsenalServiceImpl  implements AppArsenalService  {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AppArsenalServiceImpl.class.getName());

	@Inject
	AppArsenalRepository appArsenalRepository;

	@Inject
	AppArsenalRepositorySupportedPanache appArsenalRepositorySupportedPanache;

	@Inject
	AppArsenalMapper appArsenalMapper;
	
	

	@Override
	public Multi<AppArsenalResponseModel> findAll() {
		return appArsenalRepository.findAll().map(entity -> appArsenalMapper.toModel(entity));
	}

	@Override
	public Multi<AppArsenalResponseModel> findAll(final int pageIndex, final int size, long total) {

		return 
				appArsenalRepositorySupportedPanache.findAll(pageIndex, size)
					.map(entity -> appArsenalMapper.toModel(entity));
	}

	@Override
	public Uni<AppArsenalResponseModel> findById(Long id) {
		return appArsenalRepository.findById(id).map(entity -> appArsenalMapper.toModel(entity));
	}

	@Override
	public Uni<AppArsenalResponseModel> save(AppArsenalRequestModel productModel) {
		return appArsenalRepository.save(appArsenalMapper.toEntity(productModel, null))
				.map(entity -> appArsenalMapper.toModel(entity));
	}

	@Override
	public Uni<AppArsenalResponseModel> update(AppArsenalRequestModel productModel, Long id) {
		return appArsenalRepository.update(appArsenalMapper.toEntity(productModel, id))
				.map(entity -> appArsenalMapper.toModel(entity));
	}

	@Override
	public Uni<AppArsenalResponseModel> delete(Long id) {
		return appArsenalRepository.delete(AppArsenalEntity.builder().id(id).build())
				.map(entity -> appArsenalMapper.toModel(entity));
	}

	@Override
	public Uni<Long> count() {
		return appArsenalRepositorySupportedPanache.count();
	}

}
