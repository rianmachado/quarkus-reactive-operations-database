package rian.example.reative.database.service;

import rian.example.reative.database.model.Pageable;


public class AppArsenalSupportPage {

	public Pageable buidPage(final int page, final int size) {
		Pageable pageable = Pageable.builder().offset(page * size).pageSize(size).pageNumber(page).build();
		return pageable;

	}

	public long getTotalPage(final int size, final long total) {
		return total % size == 0 ? (total / size) : (total / size) + total % size;
	}

}
