package org.backend.controller;

import org.backend.model.Coupons;
import org.backend.service.CouponsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/coupons")
public class CouponsController {
    private final CouponsService couponsService;

    public CouponsController(CouponsService couponsService) {
        this.couponsService = couponsService;
    }

    @GetMapping
    public Iterable<Coupons> getAllCoupons() {
        return couponsService.getAllCoupons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coupons> getCouponById(@PathVariable UUID id) {
        Optional<Coupons> coupon = couponsService.getCouponById(id);
        return coupon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Coupons createCoupon(@RequestBody Coupons coupons) {
        return couponsService.createCoupon(coupons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coupons> updateCoupon(@PathVariable UUID id, @RequestBody Coupons coupons) {
        Coupons updatedCoupon = couponsService.updateCoupon(id, coupons);
        return new ResponseEntity<>(updatedCoupon, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable UUID id) {
        couponsService.deleteCoupon(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
