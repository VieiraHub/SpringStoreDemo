package com.vieiraatelier.demostore.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7450793061443203175L;

	public ObjectNotFoundException(String msg) { super(msg); }
	
	public ObjectNotFoundException(String msg, Throwable cause) { super(msg, cause); }
}
