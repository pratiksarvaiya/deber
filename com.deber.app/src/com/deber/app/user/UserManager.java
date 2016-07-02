package com.deber.app.user;

import java.util.List;

import com.deber.data.CategoryNormalisedValue;
import com.deber.data.user.UserDAO;
import com.deber.provider.UserProvider;

public class UserManager {

	UserDAO userDAO;
	public UserManager()
	{
		userDAO = UserProvider.getDAO();
	}
	
	public List<CategoryNormalisedValue> getAffinitiesForUser(String userId)
	{
		return userDAO.getAffinitiesForUser(userId);
	}
}
