package org.backend.model.products.exception;

public class ProductServiceException extends RuntimeException {
  public ProductServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
