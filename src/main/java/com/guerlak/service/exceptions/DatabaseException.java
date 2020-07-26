package com.guerlak.service.exceptions;

public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DatabaseException(Object id) {
		super("Database not found. Id: " + id);
	}
}
