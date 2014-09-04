package com.mbm.librarymanagement.exception;

public class LibraryManagementException extends Exception {
	private static final long serialVersionUID = 1L;
	private ExceptionCategory exceptionCategory;
	
	public LibraryManagementException(ExceptionCategory exceptionCategory) {
		this.exceptionCategory = exceptionCategory;
	}
	
	public ExceptionCategory getExceptionCategory() {
		return exceptionCategory;
	}

}
