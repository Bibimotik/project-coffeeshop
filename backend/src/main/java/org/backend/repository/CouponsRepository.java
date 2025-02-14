package org.backend.repository;

import org.backend.model.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponsRepository extends JpaRepository<Coupons, UUID> {
}
