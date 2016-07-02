package com.deber.data.dynamodb.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.deber.data.CategoryNormalisedValue;
import com.deber.data.Location;
import com.deber.data.dynamodb.BaseDDBDAO;
import com.deber.data.task.Task;
import com.deber.data.task.TaskDAO;
import static java.util.stream.Collectors.toList;

public class TaskDDBDAO extends BaseDDBDAO implements TaskDAO {

	private TaskDDB getTaskDDB(Task task) {
		TaskDDB ddbM = new TaskDDB();
		ddbM.copyDataModel(task);
		ddbM.setAddress(task.getLocation().getAddress());
		ddbM.setLocationName(task.getLocation().getName());
		ddbM.setLongitude(task.getLocation().getLongitude());
		ddbM.setLatitude(task.getLocation().getLatitude());
		return ddbM;
	}

	private Task getTask(TaskDDB task) {
		Task retVal = task.convertToDataModel();
		retVal.setCategoryNormalisedValues(getCategoryNormalisedValuesForTask(task.getTaskId()));
		retVal.setLocation(getLocationFromTask(task));
		return retVal;
	}

	private List<RelevanceDDB> getRelevancesFromTask(Task task) {

		return task.getCategoryNormalisedValues().stream().map(r -> {
			RelevanceDDB toAdd = new RelevanceDDB();
			toAdd.setTaskId(task.getTaskId());
			toAdd.setCategoryId(r.getCategoryId());
			toAdd.setRelevance(r.getNormalValue());
			return toAdd;
		}).collect(toList());
	}

	@Override
	public void createTask(Task task) {

		TaskDDB toSaveTask = getTaskDDB(task);
		List<RelevanceDDB> toSaveRelevances = getRelevancesFromTask(task);
		getMapper().save(toSaveTask);
		getMapper().batchSave(toSaveRelevances);
	}

	@Override
	public List<Task> getActiveTaskInBound(double minLat, double minLon, double maxLat, double maxLon) {

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":now", new AttributeValue().withS(sdf.format(now)));
		map.put(":minLat", new AttributeValue().withN(Double.toString(minLat)));
		map.put(":maxLat", new AttributeValue().withN(Double.toString(maxLat)));
		map.put(":minLon", new AttributeValue().withN(Double.toString(minLon)));
		map.put(":maxLon", new AttributeValue().withN(Double.toString(maxLon)));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression(
						"EndTime > :now AND Latitude BETWEEN :minLat AND :maxLat AND Longitude BETWEEN :minLon AND :maxLon")
				.withExpressionAttributeValues(map);

		List<TaskDDB> tasks = getMapper().scan(TaskDDB.class, scanExpression);
		return tasks.stream().map(r -> getTask(r)).collect(toList());
	}

	private List<CategoryNormalisedValue> getCategoryNormalisedValuesForTask(String taskId) {
		Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":id", new AttributeValue().withS(taskId));

		DynamoDBQueryExpression<RelevanceDDB> expr = new DynamoDBQueryExpression<RelevanceDDB>()
				.withKeyConditionExpression("TaskId = :id").withExpressionAttributeValues(map);

		return getMapper().query(RelevanceDDB.class, expr).stream().map(s -> {
			CategoryNormalisedValue nv = new CategoryNormalisedValue();
			nv.setCategoryId(s.getCategoryId());
			nv.setNormalValue(s.getRelevance());
			return nv;
		}).collect(toList());
	}

	private Location getLocationFromTask(TaskDDB task) {
		Location retVal = new Location();
		retVal.setAddress(task.getAddress());
		retVal.setName(task.getLocationName());
		retVal.setLatitude(task.getLatitude());
		retVal.setLongitude(task.getLongitude());
		return retVal;
	}

}
