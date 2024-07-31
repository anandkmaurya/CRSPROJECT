package com.gniot.crs.exception;

public class CourseCatalogRetrievalException extends CRSException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseCatalogRetrievalException(String message) {
        super(message);
    }

    public CourseCatalogRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
