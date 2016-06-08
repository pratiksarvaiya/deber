package com.deber.api.viewmodels;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.deber.data.task.Task;

public class TaskView implements View<Task> {
	
	private String taskId;
	private String taskName;
    private String description;
    private LocationView location;
    private String difficultyLevel;
    private Date endTime;
    private int noOfPeopleRequired;
    List<CategoryNormalisedValueView> categoryNormalisedValues;
    
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	public LocationView getLocation() {
		return location;
	}
	public void setLocation(LocationView location) {
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
	public List<CategoryNormalisedValueView> getCategoryNormalisedValues() {
		return categoryNormalisedValues;
	}
	public void setCategoryNormalisedValues(List<CategoryNormalisedValueView> categoryNormalisedValues) {
		this.categoryNormalisedValues = categoryNormalisedValues;
	}
	
	@Override
	public Task convertToDataModel() {
		return new ModelMapper().map(this, Task.class);
	}
	@Override
	public void copyDataModel(Task model) {
		new ModelMapper().map(model, this);
	}
    
}
