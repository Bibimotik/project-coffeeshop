package org.backend.Application.Service;

import org.backend.API.Mappers.OrderMapper;
import org.backend.Application.DTO.OrderDTO;
import org.backend.Application.Interfaces.IOrderService;
import org.backend.Domain.Constants.OrderConstants;
import org.backend.Domain.Model.Order;
import org.backend.Persistence.Repository.OrderRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
		Optional<Order> qwe = orderRepository.findById(id);

		// TODO просто получаем client id
		UUID q = qwe.get().getClientId();

		return CompletableFuture.supplyAsync(() -> orderRepository.findById(id));
	}

	@Async
	public CompletableFuture<Order> createAsync(UUID clientId) {
		// TODO вместо передачи 0 цены в request мы по умолчанию задаем такую цену
		var order = OrderMapper.toEntity(clientId, OrderConstants.BASE_ORDER_AMOUNT);

		return CompletableFuture.supplyAsync(() -> orderRepository.save(order));
	}
}
