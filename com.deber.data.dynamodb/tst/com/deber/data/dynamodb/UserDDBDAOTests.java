package com.deber.data.dynamodb;

import java.util.List;

import org.junit.Test;

import com.deber.data.CategoryNormalisedValue;
import com.deber.data.dynamodb.user.UserDDBDAO;

public class UserDDBDAOTests {
	
	@Test
	public void testGetAffinitiesForUser()
	{
		UserDDBDAO dao = new UserDDBDAO();
		List<CategoryNormalisedValue> values = dao.getAffinitiesForUser("User1");
		assert(values.size() > 0);
	}
}
