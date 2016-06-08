package com.deber.data.dynamodb.category;

import org.modelmapper.ModelMapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.deber.data.category.Category;
import com.deber.data.dynamodb.DDBMapped;

@DynamoDBTable(tableName = "Categories")
public class CategoryDDB implements DDBMapped<Category> {

	private String categoryId;
	  private String group;
	  
	  @DynamoDBHashKey(attributeName="CategoryId")
	  public String getCategoryId()
	  {
	    return this.categoryId;
	  }
	  
	  public void setCategoryId(String categoryId)
	  {
	    this.categoryId = categoryId;
	  }
	  
	  @DynamoDBAttribute(attributeName="Group")
	  public String getGroup()
	  {
	    return this.group;
	  }
	  
	  public void setGroup(String group)
	  {
	    this.group = group;
	  }
	  
	  @DynamoDBIgnore
	  public Category convertToDataModel()
	  {
	    return (Category)new ModelMapper().map(this, Category.class);
	  }
	  
	  @DynamoDBIgnore
	  public void copyDataModel(Category dataModel)
	  {
	    new ModelMapper().map(dataModel, this);
	  }
}
