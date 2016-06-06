package com.deber.util.lambda;

public interface Project<InputType, OutputType> {
	OutputType project(InputType input); 
}
