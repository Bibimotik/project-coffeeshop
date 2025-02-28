package org.backend.Application.Interfaces;

import org.backend.Application.DTO.CouponDTO;
import org.backend.Domain.Model.Coupon;

import java.util.Optional;
import java.util.UUID;

public interface ICouponsService {
    Iterable<Coupon> getAllCoupons();
    Optional<Coupon> getCouponById(UUID id);
    Coupon createCoupon(CouponDTO couponDTO);
    Coupon updateCoupon(UUID id, Coupon coupons);
    void stopCoupons(UUID id);
    void deleteCoupon(UUID id);
}
