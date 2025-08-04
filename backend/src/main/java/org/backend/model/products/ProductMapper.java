package org.backend.model.products;

import org.backend.model.products.DTO.ProductRequestDto;
import org.backend.model.products.DTO.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  ProductResponseDto toDto(Product product);
  Product toEntity(ProductRequestDto productRequestDto);
}
