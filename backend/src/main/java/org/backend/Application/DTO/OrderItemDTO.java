package org.backend.Application.DTO;

import org.backend.Domain.Model.Goods;
import org.backend.Domain.Model.Order;

import java.math.BigDecimal;

public record OrderItemDTO(
        Order orderId,
        Goods goodsId,
        int quantity,
        BigDecimal price
) {
}
