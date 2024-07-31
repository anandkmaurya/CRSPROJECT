package com.gniot.crs.exception;

public class EnrollmentException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnrollmentException(String message, Throwable cause) { // Include cause for SQL errors
        super(message, cause);
    }
}
