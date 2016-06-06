package com.deber.api.exceptions;

public class InternalErrorException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8313840361757704956L;
	
	private static final String PREFIX = "INT_ERROR: ";

    public InternalErrorException(String s, Exception e) {
        super(PREFIX + s, e);
    }

    public InternalErrorException(String s) {
        super(PREFIX + s);
    }
    

}
