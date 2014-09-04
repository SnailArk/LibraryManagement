package com.mbm.librarymanagement.exception;

public enum ExceptionCategory {
	SYSTEM("There is system error, please contact system administration"), INVALID_EMAIL(
			"Please specify valid email."), EMAIL_ALREADY_REGISTERED(
			"User with specified email already exists."),

	STUDENT_NOT_REGISTERED("Student is not registered into database"), BOOK_NOT_REGISTERED(
			"Book is not registered into database"), BOOK_ALREADY_ISSUED(
			"Book is already issued."), BOOK_IS_ISSUED_TO_OTHER_STUDENT(
			"Book is issued to other student."), BOOK_ALREADY_RECEIVED(
			"Book is already receivced."), PASSWORD_INCORRECT(
			"Username or password incorrect. Please try again");

	private String message;

	private ExceptionCategory(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
