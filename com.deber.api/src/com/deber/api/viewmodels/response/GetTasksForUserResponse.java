package com.deber.api.viewmodels.response;

import java.util.List;

import com.deber.api.viewmodels.TaskView;

public class GetTasksForUserResponse {
	
	List<TaskView> tasks;

	public List<TaskView> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskView> tasks) {
		this.tasks = tasks;
	}
	
	

}
