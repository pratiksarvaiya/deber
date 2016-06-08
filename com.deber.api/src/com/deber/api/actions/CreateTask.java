package com.deber.api.actions;

import com.amazonaws.services.lambda.runtime.Context;
import com.deber.api.request.CreateTaskRequest;
import com.deber.api.response.CreateTaskResponse;
import com.deber.api.viewmodels.TaskView;
import com.deber.app.task.TaskManager;
import com.deber.data.task.Task;
import com.google.gson.JsonObject;

public class CreateTask extends AbstractAction {

	TaskManager taskManager;
	public CreateTask() {
		taskManager = new TaskManager();
	}
	@Override
	public String handle(JsonObject request, Context lambdaContext) {
		TaskView taskView = getGson().fromJson(request, CreateTaskRequest.class).getTask();
		Task task = taskView.convertToDataModel();
		String taskId = taskManager.createTask(task, lambdaContext.getIdentity().getIdentityId());
		CreateTaskResponse response = new CreateTaskResponse();
		response.setTaskId(taskId);
		return getGson().toJson(response);
	}

}
