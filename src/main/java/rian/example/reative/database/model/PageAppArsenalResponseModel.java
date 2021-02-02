package rian.example.reative.database.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageAppArsenalResponseModel {

	private List<AppArsenalResponseModel> content;

	private Pageable pageable;

	private boolean last;

	private int size;

	private int number;

	private boolean empty;

	private Sort sort;

	private long totalElements;

	private long totalPages;

	private boolean first;

	public PageAppArsenalResponseModel content(List<AppArsenalResponseModel> content) {
		this.content = content;
		return this;
	}

	public PageAppArsenalResponseModel addContentItem(AppArsenalResponseModel contentItem) {
		if (this.content == null) {
			this.content = new ArrayList<AppArsenalResponseModel>();
		}
		this.content.add(contentItem);
		return this;
	}

}
