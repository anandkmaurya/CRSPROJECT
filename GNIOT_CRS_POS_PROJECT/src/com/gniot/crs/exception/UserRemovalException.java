package com.gniot.crs.exception;

public class UserRemovalException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserRemovalException(String message) {
        super(message);
    }

    public UserRemovalException(String message, Throwable cause) {
        super(message, cause);
    }

}
