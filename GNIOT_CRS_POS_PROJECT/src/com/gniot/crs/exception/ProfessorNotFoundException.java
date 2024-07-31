package com.gniot.crs.exception;

public class ProfessorNotFoundException extends CRSException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfessorNotFoundException(String message) {
        super(message);
    }

    public ProfessorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
