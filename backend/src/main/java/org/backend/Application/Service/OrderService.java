package org.backend.Application.Service;

import org.backend.API.Mappers.OrderMapper;
import org.backend.Application.DTO.OrderDTO;
import org.backend.Application.Interfaces.IOrderService;
import org.backend.Domain.Model.Order;
import org.backend.Persistence.Repository.OrderRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService implements IOrderService {
	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Async
	public CompletableFuture<Iterable<Order>> getAsync() {
		return CompletableFuture.supplyAsync(orderRepository::findAll);
	}

	@Async
	public CompletableFuture<Optional<Order>> getAsync(UUID id) {
		return CompletableFuture.supplyAsync(() -> orderRepository.findById(id));
	}

	@Async
	public CompletableFuture<Order> createAsync(OrderDTO orderDTO) {
		var order = OrderMapper.toEntity(orderDTO);

		return CompletableFuture.supplyAsync(() -> orderRepository.save(order));
	}
}
