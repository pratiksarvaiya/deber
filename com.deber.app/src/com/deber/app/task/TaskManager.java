package com.deber.app.task;

import com.deber.data.task.Task;
import com.deber.data.task.TaskDAO;
import com.deber.provider.TaskProvider;
import com.deber.util.GUID;

public class TaskManager {
	
	TaskDAO taskDAO;
	public TaskManager()
	{
		taskDAO = TaskProvider.getDAO();
	}
	
	public String createTask(Task task, String userId)
	{
		task.setTaskId(GUID.getGUID());
		task.setCreatorUserId(userId);
		taskDAO.createTask(task);
		return task.getTaskId();
	}

}
