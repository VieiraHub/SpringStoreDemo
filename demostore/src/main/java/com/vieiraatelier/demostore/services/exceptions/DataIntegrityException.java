package com.vieiraatelier.demostore.services.exceptions;

public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 7450793061443203175L;

	public DataIntegrityException(String msg) { super(msg); }
	
	public DataIntegrityException(String msg, Throwable cause) { super(msg, cause); }
}
