package com.deber.api.viewmodels.response;

import java.util.List;

import com.deber.api.viewmodels.CategoryView;

public class GetCategoryResponse {

	List<CategoryView> items;

	public List<CategoryView> getItems() {
		return items;
	}
	public void setItems(List<CategoryView> items) {
		this.items = items;
	}

}
