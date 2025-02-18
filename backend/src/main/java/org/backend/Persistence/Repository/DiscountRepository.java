package org.backend.Persistence.Repository;

import org.backend.Domain.Model.Discount;
import org.springframework.data.repository.CrudRepository;

public interface DiscountRepository extends CrudRepository<Discount, Integer> {
}
