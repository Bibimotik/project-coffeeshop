package org.backend.API.Controller;

import org.backend.Application.DTO.OrderDTO;
import org.backend.Application.Interfaces.IOrderService;
import org.backend.Domain.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin/orders")
public class OrderController {
	@Autowired
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
	//TODO как будто нам не нужно передавать туда нулевую стоимость заказа при создании
	//TODO еще и в @PathVariable теперь можно передавать clientId возможно
	public CompletableFuture<Order> create(@RequestBody UUID clientId) {
		return orderService.createAsync(clientId);
	}
}