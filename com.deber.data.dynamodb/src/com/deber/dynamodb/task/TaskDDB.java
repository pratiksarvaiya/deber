package com.deber.dynamodb.task;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.deber.data.dynamodb.DDBMapped;
import com.deber.data.task.Task;

@DynamoDBTable(tableName = "Task")
public class TaskDDB implements DDBMapped<Task> {

	private String taskId;
	private String creatorId;
	private String taskName;
	private String description;
	private String locationName;
	private String address;
	private double latitude;
	private double longitude;
	private String difficultyLevel;
	private Date endTime;
	private int noOfPeopleRequired;

	@DynamoDBHashKey(attributeName = "TaskId")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@DynamoDBAttribute(attributeName = "CreatorId")
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	@DynamoDBAttribute(attributeName = "TaskName")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@DynamoDBAttribute(attributeName = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@DynamoDBAttribute(attributeName = "LocationName")
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@DynamoDBAttribute(attributeName = "Address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@DynamoDBAttribute(attributeName = "Latitude")
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@DynamoDBAttribute(attributeName = "Longitude")
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@DynamoDBAttribute(attributeName = "DifficultyLevel")
	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	@DynamoDBAttribute(attributeName = "EndTime")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@DynamoDBAttribute(attributeName = "NoOfPeopeleRequired")
	public int getNoOfPeopleRequired() {
		return noOfPeopleRequired;
	}

	public void setNoOfPeopleRequired(int noOfPeopleRequired) {
		this.noOfPeopleRequired = noOfPeopleRequired;
	}

	@DynamoDBIgnore
	@Override
	public Task convertToDataModel() {
		return new ModelMapper().map(this, Task.class);
	}

	@DynamoDBIgnore
	@Override
	public void copyDataModel(Task paramDataModel) {
		new ModelMapper().map(paramDataModel, this);
	}

}
