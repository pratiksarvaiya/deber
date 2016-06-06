package com.deber.app.category;

import java.util.List;

import com.deber.data.category.Category;
import com.deber.data.category.CategoryDAO;
import com.deber.provider.CategoryProvider;

public class CategoryManager {
	
	private CategoryDAO categoryDAO;
	public CategoryManager()
	{
		categoryDAO = CategoryProvider.getDAO();
	}
	
	public List<Category> getAllCategories()
	{
		return categoryDAO.getAllCategories();
	}
}
