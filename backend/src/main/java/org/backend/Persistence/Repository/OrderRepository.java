package org.backend.Persistence.Repository;

import org.backend.Domain.Model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {
}
