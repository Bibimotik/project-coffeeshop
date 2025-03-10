package org.backend.API.Controller;

import org.backend.Application.DTO.CouponDTO;
import org.backend.Domain.Model.Coupon;
import org.backend.Application.Service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin/coupons")
public class CouponController {
    private final CouponService couponsService;

    public CouponController(CouponService couponsService) {
        this.couponsService = couponsService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<Iterable<Coupon>>> getAllCoupons() {
        return couponsService.getAllCoupons()
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Coupon>> getCouponById(@PathVariable UUID id) {
        return couponsService.getCouponById(id)
                .thenApply(couponOpt -> couponOpt.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Coupon>> createCoupon(@RequestBody CouponDTO couponsDTO) {
        return couponsService.createCoupon(couponsDTO)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Coupon>> updateCoupon(@PathVariable UUID id, @RequestBody CouponDTO couponDTO) {
        return couponsService.updateCoupon(id, couponDTO)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/stop/{id}")
    public CompletableFuture<ResponseEntity<Object>> stopCoupons(@PathVariable UUID id) {
        return CompletableFuture.runAsync(() -> couponsService.stopCoupons(id))
                .thenApply(unused -> ResponseEntity.ok().build())
                .exceptionally(throwable -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Object>> deleteCoupon(@PathVariable UUID id) {
        return CompletableFuture.runAsync(() -> couponsService.deleteCoupon(id))
                .thenApply(unused -> ResponseEntity.noContent().build())
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
