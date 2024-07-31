package com.gniot.crs.exception;

public class EnrolledStudentsRetrievalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnrolledStudentsRetrievalException(String message) {
        super(message);
    }

    public EnrolledStudentsRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
