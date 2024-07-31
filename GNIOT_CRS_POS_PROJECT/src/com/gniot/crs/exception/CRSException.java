package com.gniot.crs.exception;

public class CRSException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CRSException(String message) {
        super(message);
    }

    public CRSException(String message, Throwable cause) {
        super(message, cause);
    }
}