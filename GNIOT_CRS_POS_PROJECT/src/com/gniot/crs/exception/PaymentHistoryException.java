package com.gniot.crs.exception;

public class PaymentHistoryException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentHistoryException(String message) {
        super(message);
    }

    public PaymentHistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}