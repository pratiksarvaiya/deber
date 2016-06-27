package com.deber.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.RootPaneUI;

import org.junit.Test;

import com.deber.api.exceptions.BadRequestException;
import com.deber.api.exceptions.InternalErrorException;
import com.deber.api.viewmodels.CategoryNormalisedValueView;
import com.deber.api.viewmodels.LocationView;
import com.deber.api.viewmodels.TaskView;
import com.deber.api.viewmodels.request.CreateTaskRequest;
import com.deber.api.viewmodels.response.CreateTaskResponse;
import com.deber.api.viewmodels.response.GetCategoryResponse;
import com.deber.data.CategoryNormalisedValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ActionTest {

	protected Gson getGson() {

		return new GsonBuilder()
				// .enableComplexMapKeySerialization()
				// .serializeNulls()
				.setDateFormat(DateFormat.FULL)
				// .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				.setPrettyPrinting().create();
	}

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
		GetCategoryResponse resp = getGson().fromJson(getString(res), GetCategoryResponse.class);
		assert (resp.getItems().size() > 0);
	}

	@Test
	public void testCreateTask() {
		RequestRouter router = new RequestRouter();
		TaskView task = new TaskView();
		task.setTaskName("Test Task 1");
		task.setDescription("Line 1\nLine 2\nLine 3");
		task.setDifficultyLevel("diff 3");
		task.setNoOfPeopleRequired(3);
		Date d = new Date(117, 0, 1, 12, 0);
		task.setEndTime(d);

		LocationView location = new LocationView();
		location.setAddress("Add 1");
		location.setName("zero zero");
		location.setLatitude(0.0D);
		location.setLongitude(0.0D);
		task.setLocation(location);

		List<CategoryNormalisedValueView> relevances = new ArrayList<>();
		CategoryNormalisedValueView v1 = new CategoryNormalisedValueView();
		v1.setCategoryId("Category1");
		v1.setNormalValue(0.5F);
		CategoryNormalisedValueView v2 = new CategoryNormalisedValueView();
		v2.setCategoryId("Category2");
		v2.setNormalValue(0.5F);
		relevances.add(v1);
		relevances.add(v2);
		task.setCategoryNormalisedValues(relevances);

		CreateTaskRequest req = new CreateTaskRequest();
		req.setTask(task);
		String body = getGson().toJson(req);
		String model = "{\"action\" : \"com.deber.api.actions.CreateTask\", \"body\" : " + body + "}";

		ByteArrayOutputStream res = new ByteArrayOutputStream();
		try {
			router.lambdaHandler(getInputStream(model), res, new TestContext());
		} catch (InternalErrorException | BadRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateTaskResponse resp = getGson().fromJson(getString(res), CreateTaskResponse.class);
		assert (resp != null);
	}
}
