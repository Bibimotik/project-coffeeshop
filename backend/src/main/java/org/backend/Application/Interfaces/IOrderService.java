package org.backend.Application.Interfaces;

import org.backend.Application.DTO.OrderDTO;
import org.backend.Domain.Model.Order;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IOrderService {
	CompletableFuture<Iterable<Order>> getAsync();

	CompletableFuture<Optional<Order>> getAsync(UUID id);

	CompletableFuture<Order> createAsync(OrderDTO order);
}
