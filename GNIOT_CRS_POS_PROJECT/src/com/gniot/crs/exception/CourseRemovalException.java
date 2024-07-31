package com.gniot.crs.exception;

public class CourseRemovalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseRemovalException(String message) {
        super(message);
    }

    public CourseRemovalException(String message, Throwable cause) {
        super(message, cause);
    }
}
