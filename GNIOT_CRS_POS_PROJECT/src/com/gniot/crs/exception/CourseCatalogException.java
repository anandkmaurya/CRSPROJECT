package com.gniot.crs.exception;

public class CourseCatalogException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseCatalogException(String message) {
        super(message);
    }

    public CourseCatalogException(String message, Throwable cause) {
        super(message, cause);
    }
}