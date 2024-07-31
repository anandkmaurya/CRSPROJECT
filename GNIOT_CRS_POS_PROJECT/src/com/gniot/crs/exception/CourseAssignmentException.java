package com.gniot.crs.exception;

public class CourseAssignmentException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseAssignmentException(String message) {
        super(message);
    }

    public CourseAssignmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
