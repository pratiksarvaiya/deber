package com.deber.api.actions;

import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.deber.api.viewmodels.IdentityTestResponseModel;

public class IdentityTest extends AbstractAction {

	public IdentityTest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String handle(String model, Context context) {
		CognitoIdentity identity = context.getIdentity();
		IdentityTestResponseModel retVal = new IdentityTestResponseModel();
		String identityId = identity == null ? "" : identity.getIdentityId();
		retVal.setCognitoId(identityId);
		return getGson().toJson(retVal, IdentityTestResponseModel.class);
	}

}
