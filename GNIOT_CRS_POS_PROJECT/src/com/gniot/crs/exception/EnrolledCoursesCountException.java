package com.gniot.crs.exception;

public class EnrolledCoursesCountException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnrolledCoursesCountException(String message) {
        super(message);
    }

    public EnrolledCoursesCountException(String message, Throwable cause) {
        super(message, cause);
    }
}
