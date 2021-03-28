package com.vieiraatelier.demostore.services.exceptions;

public class FileException extends RuntimeException {
	private static final long serialVersionUID = -1415522156539621686L;

	public FileException(String msg) {
		super(msg);
	}
	
	public FileException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
