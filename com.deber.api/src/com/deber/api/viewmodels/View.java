package com.deber.api.viewmodels;

public interface View<DataModel> {
	
	DataModel convertToDataModel();
	void copyDataModel(DataModel model);
}
