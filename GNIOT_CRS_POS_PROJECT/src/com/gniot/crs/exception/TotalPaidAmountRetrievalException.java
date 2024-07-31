package com.gniot.crs.exception;

public class TotalPaidAmountRetrievalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TotalPaidAmountRetrievalException(String message) {
        super(message);
    }

    public TotalPaidAmountRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
