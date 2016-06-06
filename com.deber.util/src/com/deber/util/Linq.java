package com.deber.util;

import java.util.ArrayList;
import java.util.List;

public class Linq {
	public static <InputType, OutputType> List<OutputType> select(List<InputType> lst, Project<InputType, OutputType> project){
		List<OutputType> retVal = new ArrayList<>(lst.size());
		for(InputType item : lst){
			retVal.add(project.project(item));
		}
		return retVal;
	}
}
