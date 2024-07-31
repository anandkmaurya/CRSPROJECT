package com.gniot.crs.exception;

public class PendingStudentsRetrievalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PendingStudentsRetrievalException(String message) {
        super(message);
    }

    public PendingStudentsRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
