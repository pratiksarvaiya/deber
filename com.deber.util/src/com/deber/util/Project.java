package com.deber.util;

public interface Project<InputType, OutputType> {
	OutputType project(InputType input); 
}
