package com.deber.api.actions;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.deber.api.viewmodels.LocationView;
import com.deber.api.viewmodels.TaskView;
import com.deber.api.viewmodels.request.GetTasksForUserRequest;
import com.deber.api.viewmodels.response.GetTasksForUserResponse;
import com.deber.app.task.TaskManager;
import com.deber.data.task.Task;
import com.google.gson.JsonObject;

public class GetTasksForUser extends AbstractAction {

	TaskManager taskManager;

	public GetTasksForUser() {
		taskManager = new TaskManager();
	}

	@Override
	public String handle(JsonObject request, Context lambdaContext) {
		LocationView location = getGson().fromJson(request, GetTasksForUserRequest.class).getLocation();
		
		List<Task> tasks = taskManager
				.getTasksForUser(lambdaContext.getIdentity().getIdentityId(), 
						location.getLatitude(), 
						location.getLongitude());
		
		GetTasksForUserResponse retVal = new GetTasksForUserResponse();
		retVal.setTasks(convertAllToViewModel(tasks, TaskView.class));
				
		return getGson().toJson(retVal);
	}

}
