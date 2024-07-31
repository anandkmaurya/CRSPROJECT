package com.gniot.crs.exception;

public class CourseAdditionException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseAdditionException(String message) {
        super(message);
    }

    public CourseAdditionException(String message, Throwable cause) {
        super(message, cause);
    }
}
