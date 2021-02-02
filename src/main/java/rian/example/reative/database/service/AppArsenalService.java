package rian.example.reative.database.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import rian.example.reative.database.model.AppArsenalRequestModel;
import rian.example.reative.database.model.AppArsenalResponseModel;

public interface AppArsenalService {

	Multi<AppArsenalResponseModel> findAll();

	Uni<Long> count();

	Multi<AppArsenalResponseModel> findAll(int page, int size, long total);

	Uni<AppArsenalResponseModel> findById(Long id);

	Uni<AppArsenalResponseModel> save(AppArsenalRequestModel productModel);

	Uni<AppArsenalResponseModel> update(AppArsenalRequestModel productModelRequest, Long id);

	Uni<AppArsenalResponseModel> delete(Long i);

}
