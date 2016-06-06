package com.deber.api.actions;

import com.amazonaws.services.lambda.runtime.Context;
import com.deber.api.viewmodels.CategoryView;
import com.deber.api.viewmodels.GetCategoryResponse;
import com.deber.app.category.CategoryManager;
import com.google.gson.JsonObject;

public class GetCategories extends AbstractAction {

	private CategoryManager categoryManager;
    
	public GetCategories() {
		categoryManager = new CategoryManager();
	}
    
	@Override
	public String handle(JsonObject request, Context lambdaContext) {
		GetCategoryResponse response = new GetCategoryResponse();
		response.setItems(convertAllToViewModel(categoryManager.getAllCategories(), CategoryView.class));
		return getGson().toJson(response);
	}

}
