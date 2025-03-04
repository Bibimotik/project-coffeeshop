package org.backend.API.Mappers;

import org.backend.Application.DTO.OrderDTO;
import org.backend.Domain.Model.Order;

public class OrderMapper {
    public static Order toEntity(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        return new Order(
                orderDTO.clientId(),
                orderDTO.totalAmount()
        );
    }

    public static OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderDTO(
                order.getClient(),
                order.getTotalAmount()
        );
    }
}
