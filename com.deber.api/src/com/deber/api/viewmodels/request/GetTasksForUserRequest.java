package com.deber.api.viewmodels.request;

import com.deber.api.viewmodels.LocationView;

public class GetTasksForUserRequest {
	LocationView location;

	public LocationView getLocation() {
		return location;
	}

	public void setLocation(LocationView location) {
		this.location = location;
	}
	
}
