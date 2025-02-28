package org.backend.API.Mappers;

import org.backend.Application.DTO.DiscountDTO;
import org.backend.Domain.Model.Discount;

public class DiscountMapper {
    public static Discount toEntity(DiscountDTO discountDTO) {
        if (discountDTO == null) {
            return null;
        }

        return new Discount(
                discountDTO.goods(),
                discountDTO.percent()
        );
    }

    public static DiscountDTO toDTO(Discount discount) {
        if (discount == null) {
            return null;
        }

        return new DiscountDTO(
                discount.getGoods(),
                discount.getPercent()
        );
    }
}
