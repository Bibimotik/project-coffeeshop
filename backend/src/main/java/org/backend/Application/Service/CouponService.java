package org.backend.Application.Service;

import org.backend.Domain.Interfaces.ICouponsService;
import org.backend.Domain.Model.Coupon;
import org.backend.Persistence.Repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class CouponService implements ICouponsService {
    private final CouponRepository couponsRepository;

    public CouponService(CouponRepository couponsRepository) {
        this.couponsRepository = couponsRepository;
    }

    public Iterable<Coupon> getAllCoupons() {
        return couponsRepository.findAll();
    }

    public Optional<Coupon> getCouponById(UUID id) {
        return couponsRepository.findById(id);
    }

    public Coupon createCoupon(Coupon coupons) {
        return couponsRepository.save(coupons);
    }

    public Coupon updateCoupon(UUID id, Coupon coupons) {
        Optional<Coupon> existingCoupons = couponsRepository.findById(id);

        if (existingCoupons.isPresent()) {
            Coupon existing = existingCoupons.get();

            existing.setPercent(coupons.getPercent());
            existing.setUpdateDate(LocalDate.now());
        } else {
            coupons.setCreationDate(LocalDate.now());
            coupons.setUpdateDate(LocalDate.now());
        }

        return couponsRepository.save(coupons);
    }

    public void stopCoupons(UUID id) {
        Optional<Coupon> couponsOptional = couponsRepository.findById(id);

        if (couponsOptional.isPresent()) {
            Coupon coupons = couponsOptional.get();
            coupons.setDeleted(true);
            coupons.setUpdateDate(LocalDate.now());
            couponsRepository.save(coupons);
        }
    }

    public void deleteCoupon(UUID id) {
        couponsRepository.deleteById(id);
    }
}
