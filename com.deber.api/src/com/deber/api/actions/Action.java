package com.deber.api.actions;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.JsonObject;

public interface Action {
	String handle(JsonObject request, Context lambdaContext);
}
