package com.deber.api.actions;

import com.amazonaws.services.lambda.runtime.Context;

public class TestAction extends AbstractAction {

	@Override
	public String handle(String model, Context context) {
		//TestActionRequestModel mdl = getGson().fromJson(model, TestActionRequestModel.class);
		
		return null;
	}

}
	