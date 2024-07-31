package com.gniot.crs.exception;

public class ProfessorRemovalException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfessorRemovalException(String message) {
        super(message);
    }

    public ProfessorRemovalException(String message, Throwable cause) {
        super(message, cause);
    }
}
