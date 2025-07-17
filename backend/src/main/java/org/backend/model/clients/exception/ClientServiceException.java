package org.backend.model.clients.exception;

public class ClientServiceException extends RuntimeException {
  public ClientServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}