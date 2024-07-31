package com.gniot.crs.exception;

public class GradeRetrievalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GradeRetrievalException(String message) {
        super(message);
    }

    public GradeRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}