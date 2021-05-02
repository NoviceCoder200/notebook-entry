package com.labforward.notebook.exception;

/**
 * @author apurva.patil
 * 
 * This class is used to handle custom 
 * exceptions in the application.
 *
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
	}

	public ServiceException(String errorMessage) {
		super(errorMessage);
	}

	public ServiceException(Throwable throwable) {
		super(throwable);
	}

	public ServiceException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}

}
