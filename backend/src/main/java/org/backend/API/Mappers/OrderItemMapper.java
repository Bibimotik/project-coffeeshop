package org.backend.API.Mappers;

import org.backend.Application.DTO.OrderItemDTO;
import org.backend.Domain.Model.OrderItem;

public class OrderItemMapper {
    public static OrderItem toEntity (OrderItemDTO orderItemDTO) {
        if (orderItemDTO == null)
            return null;

        return new OrderItem(
                orderItemDTO.orderId(),
                orderItemDTO.goodsId(),
                orderItemDTO.quantity(),
                orderItemDTO.price()
        );
    }

    public static OrderItem toDTO (final OrderItem orderItem) {
        if (orderItem == null)
            return null;

        return new OrderItem(
                orderItem.getOrder(),
                orderItem.getGoods(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
