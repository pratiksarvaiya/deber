package com.deber.provider;

import com.deber.data.category.CategoryDAO;
import com.deber.data.dynamodb.category.CategoryDDBDAO;

public class CategoryProvider {
	private static CategoryDAO instnace;
	public static CategoryDAO getDAO(){
		if(instnace == null){
			if(Config.type == DAOType.DynamoDB)
			instnace = new CategoryDDBDAO();
		}
		return instnace;
	}

}
