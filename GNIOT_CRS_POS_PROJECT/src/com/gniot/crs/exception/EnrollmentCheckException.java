package com.gniot.crs.exception;

public class EnrollmentCheckException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnrollmentCheckException(String message) {
        super(message);
    }

    public EnrollmentCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}