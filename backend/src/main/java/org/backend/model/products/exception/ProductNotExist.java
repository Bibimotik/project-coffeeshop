package org.backend.model.products.exception;

public class ProductNotExist extends RuntimeException {
  public ProductNotExist(String message) {
    super(message);
  }
}
