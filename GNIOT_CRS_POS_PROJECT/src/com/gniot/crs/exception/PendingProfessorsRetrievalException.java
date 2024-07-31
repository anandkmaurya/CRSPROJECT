package com.gniot.crs.exception;

public class PendingProfessorsRetrievalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PendingProfessorsRetrievalException(String message) {
        super(message);
    }

    public PendingProfessorsRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
