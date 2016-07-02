package com.deber.provider;

import com.deber.data.dynamodb.task.TaskDDBDAO;
import com.deber.data.task.TaskDAO;

public class TaskProvider {

	private static TaskDAO instance;

	public static TaskDAO getDAO() {
		if (instance == null) {
			if (Config.type == DAOType.DynamoDB) {
				instance = new TaskDDBDAO();
			}
		}
		return instance;
	}
}
