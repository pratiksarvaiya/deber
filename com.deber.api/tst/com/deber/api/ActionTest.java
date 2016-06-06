package com.deber.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;

import com.deber.api.exceptions.BadRequestException;
import com.deber.api.exceptions.InternalErrorException;
import com.deber.api.viewmodels.GetCategoryResponse;
import com.google.gson.Gson;

public class ActionTest {

	protected InputStream getInputStream(String s) {
		return new ByteArrayInputStream(s.getBytes());
	}

	protected String getString(ByteArrayOutputStream s) {
		return s.toString();
	}

	@Test
	public void testGetCategoriesAction() {
		RequestRouter router = new RequestRouter();
		String model = "{\"action\" : \"com.deber.api.actions.GetCategories\", \"body\" : {}}";
		ByteArrayOutputStream res = new ByteArrayOutputStream();
		try {
			router.lambdaHandler(getInputStream(model), res, new TestContext());
		} catch (InternalErrorException | BadRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GetCategoryResponse resp = new Gson().fromJson(getString(res), GetCategoryResponse.class);
		assert (resp.getItems().size() > 0);
	}

}
