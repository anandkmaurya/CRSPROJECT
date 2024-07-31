package com.gniot.crs.exception;

public class PaymentHistoryRetrievalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentHistoryRetrievalException(String message) {
        super(message);
    }

    public PaymentHistoryRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
