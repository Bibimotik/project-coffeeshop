package org.backend.controller;

import org.backend.model.Coupons;
import org.backend.repository.CouponsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/coupons")
public class CouponsController {
    private final CouponsRepository couponsRepository;

    public CouponsController(CouponsRepository couponsRepository) {
        this.couponsRepository = couponsRepository;
    }

    @GetMapping
    public Iterable<Coupons> getAllCoupons() {
        return couponsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Coupons> getCouponById(@PathVariable UUID id) {
        return couponsRepository.findById(id);
    }

    @PostMapping
    public Coupons createCoupon(@RequestBody Coupons coupons) {
        return couponsRepository.save(coupons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coupons> updateCoupon(@PathVariable UUID id, @RequestBody Coupons coupons) {
        return (couponsRepository.existsById(id)
                ? new ResponseEntity<>(couponsRepository.save(coupons), HttpStatus.OK)
                : new ResponseEntity<>(couponsRepository.save(coupons), HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    public void deleteCoupon(@PathVariable UUID id) {
        couponsRepository.deleteById(id);
    }
}
