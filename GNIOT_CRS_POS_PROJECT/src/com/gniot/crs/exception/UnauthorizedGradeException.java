package com.gniot.crs.exception;

public class UnauthorizedGradeException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedGradeException(String message) {
        super(message);
    }

    public UnauthorizedGradeException(String message, Throwable cause) {
        super(message, cause);
    }
}
