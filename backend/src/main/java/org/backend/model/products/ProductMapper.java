package org.backend.model.products;

import org.backend.model.products.DTO.ProductRequestDto;
import org.backend.model.products.DTO.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "category", source = "productCategory")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  ProductResponseDto toDto(Product product);

  @Mapping(target = "category", source = "categoryId", qualifiedByName = "categoryIdToCategory")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  Product toEntity(ProductRequestDto productRequestDto);

  @Named("categoryIdToCategory")
  default ProductCategory mapCategoryIdToCategory(Integer categoryId) {
    if (categoryId == null) {
      return null;
    }
    ProductCategory category = new ProductCategory();
    category.setId(categoryId);
    return category;
  }
}