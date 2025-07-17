package org.backend.model.clients.exception;

public class ClientNotExists extends RuntimeException {
  public ClientNotExists(String message) {
    super(message);
  }
}
