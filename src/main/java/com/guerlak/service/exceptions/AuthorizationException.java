package com.guerlak.service.exceptions;

public class AuthorizationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(Object msg) {
		super((String) msg);
	}
	
}
