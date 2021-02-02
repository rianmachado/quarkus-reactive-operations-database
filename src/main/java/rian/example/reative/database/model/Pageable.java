package rian.example.reative.database.model;

import io.quarkus.panache.common.Sort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pageable {

	private int offset;

	private int pageNumber;

	private int pageSize;

	private boolean paged;
	
	private Sort sort;

}
