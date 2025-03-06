package org.backend.Application.Interfaces;

import org.backend.Application.DTO.OrderItemDTO;
import org.backend.Domain.Model.OrderItem;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IOrderItemService {
	CompletableFuture<OrderItem> addAsync(OrderItemDTO orderItemDTO);
	CompletableFuture cancelAsync(UUID id);
}