package org.backend.model.clients.exception;

public class ClientAlreadyExistsException extends RuntimeException {
  public ClientAlreadyExistsException(String message) {
    super(message);
  }
}
