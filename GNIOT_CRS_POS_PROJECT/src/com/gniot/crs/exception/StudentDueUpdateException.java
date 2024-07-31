package com.gniot.crs.exception;

public class StudentDueUpdateException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentDueUpdateException(String message) {
        super(message);
    }

    public StudentDueUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
