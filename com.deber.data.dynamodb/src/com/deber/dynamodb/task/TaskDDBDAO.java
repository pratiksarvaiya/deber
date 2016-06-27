package com.deber.dynamodb.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.deber.data.dynamodb.BaseDDBDAO;
import com.deber.data.task.Task;
import com.deber.data.task.TaskDAO;
import com.deber.util.Linq;

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

	private List<RelevanceDDB> getRelevancesFromTask(Task task) {

		return Linq.select(task.getCategoryNormalisedValues(), r -> {
			RelevanceDDB toAdd = new RelevanceDDB();
			toAdd.setTaskId(task.getTaskId());
			toAdd.setCategoryId(r.getCategoryId());
			toAdd.setRelevance(r.getNormalValue());
			return toAdd;
		});
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
        		
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("EndTime > :now")
				.withExpressionAttributeValues(map);
		
		return convertAllToDataModel(getMapper().scan(TaskDDB.class, scanExpression ));
	}

}
