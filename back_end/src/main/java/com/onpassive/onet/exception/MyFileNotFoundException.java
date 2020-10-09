package com.onpassive.onet.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class MyFileNotFoundException extends RuntimeException {
   
	private static final long serialVersionUID = -5608375804125649743L;

	public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
