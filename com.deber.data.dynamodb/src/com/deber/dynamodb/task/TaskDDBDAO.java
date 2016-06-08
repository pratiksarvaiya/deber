package com.deber.dynamodb.task;

import java.util.List;

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

	private List<RelevanceDDB> getRelevances(Task task) {
		
		return Linq.select(
				task.getCategoryNormalisedValues(), 
				r -> {
					RelevanceDDB toAdd = new RelevanceDDB();
					toAdd.setTaskId(task.getTaskId());
					toAdd.setCategoryId(r.getCategoryId());
					toAdd.setRelevance(r.getNormalValue());
					return toAdd;
				}
			);
	}

	@Override
	public void createTask(Task task) {
		
		TaskDDB toSaveTask = getTaskDDB(task);
		List<RelevanceDDB> toSaveRelevances= getRelevances(task);
		getMapper().save(toSaveTask);
		getMapper().batchSave(toSaveRelevances);
	}

}
