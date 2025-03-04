package org.backend.Application.DTO;

import org.backend.Domain.Model.Goods;

public record DiscountDTO(
        Goods goodsId,
        Integer percent
) {
}
