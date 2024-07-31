package com.gniot.crs.exception;

public class StudentApprovalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentApprovalException(String message) {
        super(message);
    }

    public StudentApprovalException(String message, Throwable cause) {
        super(message, cause);
    }
}
