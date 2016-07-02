package com.deber.app.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.deber.app.user.UserManager;
import com.deber.data.CategoryNormalisedValue;
import com.deber.data.task.Task;
import com.deber.data.task.TaskDAO;
import com.deber.provider.TaskProvider;
import com.deber.util.GUID;
import com.deber.util.Sorter;

public class TaskManager {

	UserManager userManager;

	TaskDAO taskDAO;

	public TaskManager() {
		userManager = new UserManager();
		taskDAO = TaskProvider.getDAO();
	}

	public String createTask(Task task, String userId) {
		task.setTaskId(GUID.getGUID());
		task.setCreatorUserId(userId);
		taskDAO.createTask(task);
		return task.getTaskId();
	}

	public List<Task> getTasksForUser(String userId, double latitude, double longitude) {
		List<CategoryNormalisedValue> affinities = userManager.getAffinitiesForUser(userId);
		List<Task> activeTasks = taskDAO.getActiveTaskInBound(latitude - 2, longitude - 2, latitude + 2, longitude + 2);
		Map<Task, Double> matchFactors = getMatchFactors(activeTasks, affinities);
		
		return Sorter.sortByValue(matchFactors)
				.entrySet()
				.stream()
				.map(e-> e.getKey())
				.collect(Collectors.toList());
	}

	public Map<Task, Double> getMatchFactors(List<Task> tasks, List<CategoryNormalisedValue> affinities) {
		
		Map<String, Double> affinityMap = new HashMap<>();
		affinities.stream().forEach(r-> affinityMap.put(r.getCategoryId(), r.getNormalValue()));
		Map<Task, Double> retVal = new HashMap<>();
		for(Task t : tasks)
		{
			double matchFactor = 0;
			for(CategoryNormalisedValue nv : t.getCategoryNormalisedValues())
			{
				if(affinityMap.containsKey(nv.getCategoryId()))
				{
					matchFactor += nv.getNormalValue() * affinityMap.get(nv.getCategoryId());
				}
			}
			retVal.put(t, matchFactor);
		}
		return retVal;
	}
}
