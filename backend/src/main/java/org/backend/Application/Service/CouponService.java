package org.backend.Application.Service;

import org.backend.API.Mappers.CouponMapper;
import org.backend.Application.DTO.CouponDTO;
import org.backend.Application.Interfaces.ICouponsService;
import org.backend.Domain.Model.Coupon;
import org.backend.Persistence.Repository.ClientRepository;
import org.backend.Persistence.Repository.CouponRepository;
import org.backend.Persistence.Repository.GoodsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class CouponService implements ICouponsService {
    private final CouponRepository couponsRepository;
    private final ClientRepository clientRepository;
    private final GoodsRepository goodsRepository;
    private final CouponMapper couponMapper;

    public CouponService(CouponRepository couponsRepository, ClientRepository clientRepository, GoodsRepository goodsRepository, CouponMapper couponMapper) {
        this.couponsRepository = couponsRepository;
        this.clientRepository = clientRepository;
        this.goodsRepository = goodsRepository;
        this.couponMapper = couponMapper;
    }

    public Iterable<Coupon> getAllCoupons() {
        return couponsRepository.findAll();
    }

    public Optional<Coupon> getCouponById(UUID id) {
        return couponsRepository.findById(id);
    }

    public Coupon createCoupon(CouponDTO couponDTO) {
        var client = clientRepository.findById(couponDTO.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        var goods = goodsRepository.findById(couponDTO.goodsId())
                .orElseThrow(() -> new RuntimeException("Goods not found"));

        Coupon coupon = couponMapper.toEntity(client, goods, couponDTO);
        return couponsRepository.save(coupon);
    }

    public Coupon updateCoupon(UUID id, CouponDTO couponDTO) {
        Optional<Coupon> existingCoupons = couponsRepository.findById(id);

        if (existingCoupons.isPresent()) {
            Coupon existing = existingCoupons.get();

            var client = clientRepository.findById(couponDTO.clientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));

            var goods = goodsRepository.findById(couponDTO.goodsId())
                    .orElseThrow(() -> new RuntimeException("Goods not found"));

            existing.setClient(client);
            existing.setGoods(goods);
            existing.setPercent(couponDTO.percent());
            existing.setUpdateDate(LocalDate.now());

            return couponsRepository.save(existing);
        } else {
            throw new RuntimeException("Coupon not found");
        }
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