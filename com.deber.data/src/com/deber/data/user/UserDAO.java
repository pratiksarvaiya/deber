package com.deber.data.user;

import java.util.List;

import com.deber.data.CategoryNormalisedValue;

public interface UserDAO {
	List<CategoryNormalisedValue> getAffinitiesForUser(String userID);
}
