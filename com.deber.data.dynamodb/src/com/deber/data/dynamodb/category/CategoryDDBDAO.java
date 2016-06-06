package com.deber.data.dynamodb.category;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.deber.data.category.Category;
import com.deber.data.category.CategoryDAO;
import com.deber.data.dynamodb.BaseDDBDAO;

public class CategoryDDBDAO extends BaseDDBDAO implements CategoryDAO{
	
	@Override
	public List<Category> getAllCategories() {

		List<CategoryDDB> items = getMapper().scan(CategoryDDB.class, new DynamoDBScanExpression());
	    
		return convertAllToDataModel(items);
	    
	}
}
