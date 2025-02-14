package org.backend.repository;

import org.backend.model.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountsRepository extends JpaRepository<Discounts, Integer> {
}
