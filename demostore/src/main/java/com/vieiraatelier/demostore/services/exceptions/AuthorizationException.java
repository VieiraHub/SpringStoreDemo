package com.vieiraatelier.demostore.services.exceptions;

public class AuthorizationException extends RuntimeException {
	private static final long serialVersionUID = 8482983878297687253L;

	public AuthorizationException(String msg) { super(msg); }
	
	public AuthorizationException(String msg, Throwable cause) { super(msg, cause); }
}
