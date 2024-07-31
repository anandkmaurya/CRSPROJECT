package com.gniot.crs.exception;

public class BillCalculationException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BillCalculationException(String message) {
        super(message);
    }

    public BillCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}