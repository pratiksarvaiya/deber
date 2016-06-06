package com.deber.api.viewmodels;

import org.modelmapper.ModelMapper;

import com.deber.data.category.Category;

public class CategoryView implements View<Category> {

	private String categoryId;
	private String group;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	@Override
	public Category convertToDataModel() {
		return new ModelMapper().map(this, Category.class);
	}
	@Override
	public void copyDataModel(Category model) {
		new ModelMapper().map(model, this);
	}
	
	
	
}
