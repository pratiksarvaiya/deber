package com.deber.data.dynamodb;

import java.util.List;

import org.junit.Test;

import com.deber.data.dynamodb.task.TaskDDBDAO;
import com.deber.data.task.Task;

public class TaskDDBDAOTests {

	@Test
	public void testGetActiveTasksInBounds()
	{
		TaskDDBDAO dao = new TaskDDBDAO();
		List<Task> tasks = dao.getActiveTaskInBound(0, 0, 0, 0);
		assert(tasks.size() > 0);
	}
}
