package com.gniot.crs.exception;

public class EnrolledCoursesRetrievalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnrolledCoursesRetrievalException(String message) {
        super(message);
    }

    public EnrolledCoursesRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
