package org.backend.Application.Exceptions;

public class AlreadyExistException extends RuntimeException {
	public AlreadyExistException(String message) {
		super(message);
	}
}