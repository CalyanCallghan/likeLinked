package com.onpassive.onet.exception;

public class GroupNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GroupNotFoundException() {
		super();
	}
	
	public GroupNotFoundException(String message) {
		super(message);
	}
}

