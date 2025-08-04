package org.backend.model.products.DTO;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDto (
        @NotBlank(message = "Category ID is required")
        Integer categoryId,
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Image URL is required")
        String imageUrl,
        String description,
        @NotBlank(message = "Volume is required")
        String volume,
        @Positive(message = "Price must be positive")
        @Digits(integer = 10, fraction = 2, message = "Price must have up to 10 integer and 2 fraction digits")
        BigDecimal price,
        Boolean isActive
) { }