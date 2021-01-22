package rian.example.reative.database.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppArsenalRequestModel implements Serializable {
	private static final long serialVersionUID = 6583265823414333531L;
	@NotBlank
	@NotNull
	private String otherInfo;
}
