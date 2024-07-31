package com.gniot.crs.exception;

public class DueAmountUpdateException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DueAmountUpdateException(String message) {
        super(message);
    }

    public DueAmountUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
