package com.gniot.crs.exception;

public class StudentIdRetrievalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentIdRetrievalException(String message) {
        super(message);
    }

    public StudentIdRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
