package com.gniot.crs.exception;

//RemoveProfessorFromCourseException.java (in com.gniot.crs.exception package)
public class RemoveProfessorFromCourseException extends CRSException {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

// Constructors
 public RemoveProfessorFromCourseException(String message) {
     super(message);
 }

 public RemoveProfessorFromCourseException(String message, Throwable cause) {
     super(message, cause);
 }
}
