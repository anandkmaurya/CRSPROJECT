package com.gniot.crs.exception;

public class GradeUpdateException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GradeUpdateException(String message) {
        super(message);
    }

    public GradeUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
