package com.deber.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Dummy implements RequestHandler<String, String> {

	@Override
	public String handleRequest(String input, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

}
