package org.kuali.student.core.exceptions;

public class DataValidationErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public DataValidationErrorException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataValidationErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DataValidationErrorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DataValidationErrorException(Throwable cause) {
		super(cause);
	}

}