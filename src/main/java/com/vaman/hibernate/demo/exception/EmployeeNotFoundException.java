package com.vaman.hibernate.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 9093104731534881360L;

	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
