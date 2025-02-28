package org.backend.API.Controller;

import org.backend.Application.DTO.CouponDTO;
import org.backend.Domain.Model.Coupon;
import org.backend.Application.Service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/coupons")
public class CouponController {
    private final CouponService couponsService;

    public CouponController(CouponService couponsService) {
        this.couponsService = couponsService;
    }

    @GetMapping
    public Iterable<Coupon> getAllCoupons() {
        return couponsService.getAllCoupons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable UUID id) {
        Optional<Coupon> coupon = couponsService.getCouponById(id);
        return coupon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Coupon createCoupon(@RequestBody CouponDTO couponsDTO) {
        return couponsService.createCoupon(couponsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable UUID id, @RequestBody Coupon coupons) {
        Coupon updatedCoupon = couponsService.updateCoupon(id, coupons);
        return new ResponseEntity<>(updatedCoupon, HttpStatus.OK);
    }

    @PostMapping("/stop/{id}")
    public ResponseEntity<Void> stopCoupons(@PathVariable UUID id) {
        couponsService.stopCoupons(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable UUID id) {
        couponsService.deleteCoupon(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
