package org.backend.Persistence.Repository;

import org.backend.Domain.Model.Coupon;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CouponRepository extends CrudRepository<Coupon, UUID> {
}
