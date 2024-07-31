package com.gniot.crs.exception;

public class CourseNotFoundException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String message) {
        super(message);
    }
}