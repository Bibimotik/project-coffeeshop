package org.backend.API.Controller;

import org.backend.Application.DTO.OrderDTO;
import org.backend.Application.Interfaces.IOrderService;
import org.backend.Domain.Model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin/orders")
public class OrderController {
	private final IOrderService orderService;

	public OrderController(IOrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public CompletableFuture<Iterable<Order>> getAll() {
		return orderService.getAsync();
	}

	@GetMapping("/{id}")
	public CompletableFuture<Optional<Order>> get(@PathVariable UUID id) {
		return orderService.getAsync(id);
	}

	@PostMapping
	public CompletableFuture<Order> create(@RequestBody OrderDTO orderDTO) {
		// TODO почему в OrderDTO полная сущность Client вместо id
		return orderService.createAsync(orderDTO);
	}
}