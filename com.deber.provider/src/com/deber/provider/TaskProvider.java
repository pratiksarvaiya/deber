package com.deber.provider;

import com.deber.data.task.TaskDAO;
import com.deber.dynamodb.task.TaskDDBDAO;

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
