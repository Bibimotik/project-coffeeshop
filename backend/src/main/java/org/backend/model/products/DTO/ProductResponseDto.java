package org.backend.model.products.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        ProductCategoryDto category,
        String name,
        String imageUrl,
        String description,
        String volume,
        BigDecimal price,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
  public record ProductCategoryDto(
          Integer id,
          String name,
          Short displayOrder
  ) {}
}