package com.gniot.crs.exception;

public class BillAmountUpdateException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BillAmountUpdateException(String message) {
        super(message);
    }

    public BillAmountUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
