package com.deber.api.actions;

import com.amazonaws.services.lambda.runtime.Context;
import com.deber.api.viewmodels.TestActionRequestModel;
import com.deber.api.viewmodels.TestActionResponseModel;
import com.deber.data.models.Test.TestModel;
import com.deber.data.models.Test.TestModelDao;

public class DdbTestAction extends AbstractAction {

	@Override
	public String handle(String model, Context context) {
		TestModelDao dao= null;
		TestActionRequestModel mdl = getGson().fromJson(model, TestActionRequestModel.class);
		TestModel retVal = dao.get(mdl.getS1());
		TestActionResponseModel resp = new TestActionResponseModel();
		resp.setS1(retVal.getValue());
		return getGson().toJson(resp, TestActionResponseModel.class);
	}
}
