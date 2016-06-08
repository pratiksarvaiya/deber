package com.deber.data.task;

import java.util.Date;
import java.util.List;

import com.deber.data.CategoryNormalisedValue;
import com.deber.data.Location;

public class Task {
	private String taskId;
	private String creatorUserId;
	private String taskName;
    private String description;
    private Location location;
    private String difficultyLevel;
    private Date endTime;
    private int noOfPeopleRequired;
    List<CategoryNormalisedValue> categoryNormalisedValues;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getCreatorUserId() {
		return creatorUserId;
	}
	public void setCreatorUserId(String creatorUserId) {
		this.creatorUserId = creatorUserId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getNoOfPeopleRequired() {
		return noOfPeopleRequired;
	}
	public void setNoOfPeopleRequired(int noOfPeopleRequired) {
		this.noOfPeopleRequired = noOfPeopleRequired;
	}
	public List<CategoryNormalisedValue> getCategoryNormalisedValues() {
		return categoryNormalisedValues;
	}
	public void setCategoryNormalisedValues(List<CategoryNormalisedValue> categoryNormalisedValues) {
		this.categoryNormalisedValues = categoryNormalisedValues;
	}
    
}
