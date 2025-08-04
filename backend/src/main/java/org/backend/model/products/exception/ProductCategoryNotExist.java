package org.backend.model.products.exception;

public class ProductCategoryNotExist extends RuntimeException {
  public ProductCategoryNotExist(String message) {
    super(message);
  }
}
