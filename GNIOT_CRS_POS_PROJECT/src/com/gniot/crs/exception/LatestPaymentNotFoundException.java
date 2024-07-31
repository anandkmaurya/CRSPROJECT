package com.gniot.crs.exception;

public class LatestPaymentNotFoundException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LatestPaymentNotFoundException(String message) {
        super(message);
    }

    public LatestPaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}