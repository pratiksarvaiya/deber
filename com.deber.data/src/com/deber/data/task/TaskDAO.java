package com.deber.data.task;

import java.util.List;

public interface TaskDAO {
	void createTask(Task task);
	List<Task> getActiveTaskInBound(double minLat, double minLon, double maxLat, double maxLon);
}
