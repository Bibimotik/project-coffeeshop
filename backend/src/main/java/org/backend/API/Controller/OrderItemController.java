package org.backend.API.Controller;

import org.backend.Application.DTO.OrderItemDTO;
import org.backend.Application.Interfaces.IOrderItemService;
import org.backend.Domain.Model.OrderItem;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin/order-items")
public class OrderItemController {
	private final IOrderItemService orderItemService;

	public OrderItemController(IOrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	@PostMapping
	public CompletableFuture<OrderItem> add(@RequestBody OrderItemDTO orderItemDTO) {
		// TODO почему в OrderItemDTO полная сущность Client и Goods вместо их id
		return orderItemService.addAsync(orderItemDTO);
	}

	@PostMapping("/{id}")
	public CompletableFuture cancel(@PathVariable UUID id) {
		return orderItemService.cancelAsync(id);
	}
}
