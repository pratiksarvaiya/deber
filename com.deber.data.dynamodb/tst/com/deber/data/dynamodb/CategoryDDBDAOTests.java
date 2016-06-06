package com.deber.data.dynamodb;

import java.util.List;

import org.junit.Test;

import com.deber.data.category.Category;
import com.deber.data.category.CategoryDAO;
import com.deber.data.dynamodb.category.CategoryDDBDAO;

public class CategoryDDBDAOTests {

	@Test
	public void testGetAllCategories() {
		CategoryDAO dao = new CategoryDDBDAO();
		List<Category> lst =  dao.getAllCategories();
		assert(lst.size() > 0);
	}

}
