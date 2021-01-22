package rian.example.reative.database.mapper;

import java.util.Optional;

import javax.enterprise.context.Dependent;

import rian.example.reative.database.entity.AppArsenalEntity;
import rian.example.reative.database.model.AppArsenalRequestModel;
import rian.example.reative.database.model.AppArsenalResponseModel;

@Dependent
public class AppArsenalMapper {

	public AppArsenalResponseModel toModel(AppArsenalEntity appArsenalEntity) {
		
		Optional<AppArsenalResponseModel> appArsenalResponseModel = Optional
				.ofNullable(appArsenalEntity == null ? null : new AppArsenalResponseModel());
		
		if (!appArsenalResponseModel.isPresent()) {
			return AppArsenalResponseModel.builder().build();
		}

		Optional<AppArsenalEntity> entity = Optional.ofNullable(appArsenalEntity);
		
		Optional.ofNullable(entity.get().getId()).ifPresent(id -> {
			appArsenalResponseModel.get().setId(id);
		});
		
		Optional.ofNullable(entity.get().getOtherInfo()).ifPresent(otherInfo -> {
			appArsenalResponseModel.get().setOtherInfo(otherInfo);
		});
		
		return appArsenalResponseModel.get();
	}

	public AppArsenalEntity toEntity(AppArsenalRequestModel appArsenalRequestModel, Long id) {
		return AppArsenalEntity.builder().id(id).otherInfo(appArsenalRequestModel.getOtherInfo()).build();
	}

}
