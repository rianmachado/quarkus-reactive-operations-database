package rian.example.reative.database.model;

import java.io.Serializable;

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
public class AppArsenalResponseModel implements Serializable {
	private static final long serialVersionUID = -4661784289660158948L;
	private Long id;
	private String otherInfo;

	@Override
	public String toString() {
		return "AppArsenalResponseModel [id=" + id + ", otherInfo=" + otherInfo + "]";
	}

}
