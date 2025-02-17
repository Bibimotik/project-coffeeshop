package org.backend.service;

import org.backend.model.Coupons;
import org.backend.repository.CouponsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CouponsService {
    private final CouponsRepository couponsRepository;

    public CouponsService(CouponsRepository couponsRepository) {
        this.couponsRepository = couponsRepository;
    }

    public Iterable<Coupons> getAllCoupons() {
        return couponsRepository.findAll();
    }

    public Optional<Coupons> getCouponById(UUID id) {
        return couponsRepository.findById(id);
    }

    public Coupons createCoupon(Coupons coupons) {
        return couponsRepository.save(coupons);
    }

    public Coupons updateCoupon(UUID id, Coupons coupons) {
        if (couponsRepository.existsById(id)) {
            return couponsRepository.save(coupons);
        } else {
            return couponsRepository.save(coupons);
        }
    }

    public void deleteCoupon(UUID id) {
        couponsRepository.deleteById(id);
    }
}
