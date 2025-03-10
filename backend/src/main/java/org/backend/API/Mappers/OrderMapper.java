package org.backend.API.Mappers;

import org.backend.Application.DTO.OrderDTO;
import org.backend.Domain.Model.Order;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderMapper {
	public static Order toEntity(UUID clientId, BigDecimal totalAmount) {
		if (clientId == null || totalAmount == null) {
			return null;
		}

		return new Order(clientId, totalAmount);
	}

	public static OrderDTO toDTO(Order order) {
		if (order == null) {
			return null;
		}

		return new OrderDTO(order.getClientId(), order.getTotalAmount());
	}
}
