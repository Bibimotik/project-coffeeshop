package org.backend.Application.Interfaces;

import org.backend.Application.DTO.CouponDTO;
import org.backend.Domain.Model.Coupon;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ICouponsService {
    CompletableFuture<Iterable<Coupon>> getAllCoupons();
    CompletableFuture<Optional<Coupon>> getCouponById(UUID id);
    CompletableFuture<Coupon> createCoupon(CouponDTO couponDTO);
    CompletableFuture<Coupon> updateCoupon(UUID id, CouponDTO couponDTO);
    void stopCoupons(UUID id);
    void deleteCoupon(UUID id);
}
