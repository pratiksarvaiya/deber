package com.deber.api.viewmodels.request;

import com.deber.api.viewmodels.TaskView;

public class CreateTaskRequest {
	private TaskView task;

	public TaskView getTask() {
		return task;
	}
	public void setTask(TaskView task) {
		this.task = task;
	}
	
}
