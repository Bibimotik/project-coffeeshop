package org.backend.Persistence.Repository;

import org.backend.Domain.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
	boolean existsById(UUID id);
}