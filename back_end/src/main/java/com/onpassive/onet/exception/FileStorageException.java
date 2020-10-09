package com.onpassive.onet.exception;

public class FileStorageException extends RuntimeException {
   
	private static final long serialVersionUID = -7763934555689443998L;

	public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
