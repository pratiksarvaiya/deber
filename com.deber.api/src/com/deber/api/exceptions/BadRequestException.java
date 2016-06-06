package com.deber.api.exceptions;

public class BadRequestException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3759416883664032680L;
	private static final String PREFIX = "BAD_REQ: ";
    public BadRequestException(String s, Exception e) {
        super(PREFIX + s, e);
    }

    public BadRequestException(String s) {
        super(PREFIX + s);
    }
}