package org.backend.Application.Service;

import org.backend.API.Mappers.ClientMapper;
import org.backend.API.Mappers.OrderItemMapper;
import org.backend.Application.DTO.OrderItemDTO;
import org.backend.Application.Exceptions.AlreadyExistException;
import org.backend.Application.Exceptions.ResourceNotFoundException;
import org.backend.Application.Interfaces.IOrderItemService;
import org.backend.Domain.Model.Order;
import org.backend.Domain.Model.OrderItem;
import org.backend.Persistence.Repository.OrderItemRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderItemService implements IOrderItemService {
	private final OrderItemRepository orderItemRepository;

	public OrderItemService(OrderItemRepository orderItemRepository) {
		this.orderItemRepository = orderItemRepository;
	}

//	@Async
//	public CompletableFuture<Iterable<OrderItem>> getAsync(UUID id) {
//		// TODO
//		// мы получаем что?
//		// все по юзер id или все по order id
//		// а как тогда создать order раньще всех order items если в order у нас есть сумма всех товаров
//		// или оно создается нулевая, а потом меняется
////		return CompletableFuture.supplyAsync(() -> orderItemRepository.findAllByUserId(id));
//	}

	@Async
	public CompletableFuture<OrderItem> addAsync(OrderItemDTO orderItemDTO) {

		var orderItem = OrderItemMapper.toEntity(orderItemDTO);

		return CompletableFuture.completedFuture(orderItemRepository.save(orderItem));
	}

	@Override
	public CompletableFuture cancelAsync(UUID id) {
		return CompletableFuture.runAsync(() -> {
			if (orderItemRepository.existsById(id))
				throw new ResourceNotFoundException("Order item with id " + id + " not found");

			orderItemRepository.deleteById(id);
		});
	}
}