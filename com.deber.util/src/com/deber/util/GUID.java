package com.deber.util;

import java.util.UUID;

public class GUID {

	public static String getGUID()
	{
		 return UUID.randomUUID().toString();
	}
}
