package com.deber.provider;

import com.deber.data.dynamodb.user.UserDDBDAO;
import com.deber.data.user.UserDAO;

public class UserProvider {

	private static UserDAO instance;

	public static UserDAO getDAO() {
		if (instance == null) {
			if (Config.type == DAOType.DynamoDB) {
				instance = new UserDDBDAO();
			}
		}
		return instance;
	}
}
