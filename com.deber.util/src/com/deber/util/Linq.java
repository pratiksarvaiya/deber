package com.deber.util;

import java.util.ArrayList;
import java.util.List;

import com.deber.util.lambda.Assert;
import com.deber.util.lambda.Project;

public class Linq {
	public static <InputType, OutputType> List<OutputType> select(List<InputType> lst,
			Project<InputType, OutputType> project) {
		List<OutputType> retVal = new ArrayList<>(lst.size());
		for (InputType item : lst) {
			retVal.add(project.project(item));
		}
		return retVal;
	}

	public static <InputType> List<InputType> where(List<InputType> lst,
			Assert<InputType> result) {
		List<InputType> retVal = new ArrayList<>();
		for(InputType itm : lst)
		{
			if(result.assertFunction(itm))
			{
				retVal.add(itm);
			}
		}
		return retVal;
	}
}
