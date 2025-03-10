package org.backend.Application.Service;

import org.backend.API.Mappers.CouponMapper;
import org.backend.Application.DTO.CouponDTO;
import org.backend.Application.Interfaces.ICouponsService;
import org.backend.Domain.Model.Coupon;
import org.backend.Persistence.Repository.ClientRepository;
import org.backend.Persistence.Repository.CouponRepository;
import org.backend.Persistence.Repository.GoodsRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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

    @Async
    public CompletableFuture<Iterable<Coupon>> getAllCoupons() {
        return CompletableFuture.supplyAsync(couponsRepository::findAll);
    }

    @Async
    public CompletableFuture<Optional<Coupon>> getCouponById(UUID id) {
        return CompletableFuture.supplyAsync(() -> couponsRepository.findById(id));
    }

    @Async
    public CompletableFuture<Coupon> createCoupon(CouponDTO couponDTO) {
        var client = clientRepository.findById(couponDTO.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        var goods = goodsRepository.findById(couponDTO.goodsId())
                .orElseThrow(() -> new RuntimeException("Goods not found"));

        Coupon coupon = couponMapper.toEntity(client, goods, couponDTO);
        return CompletableFuture.supplyAsync(() -> couponsRepository.save(coupon));
    }

    @Async
    public CompletableFuture<Coupon> updateCoupon(UUID id, CouponDTO couponDTO) {
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

            return CompletableFuture.supplyAsync(() -> couponsRepository.save(existing));
        } else {
            throw new RuntimeException("Coupon not found");
        }
    }

    @Async
    public void stopCoupons(UUID id) {
        Optional<Coupon> couponsOptional = couponsRepository.findById(id);

        if (couponsOptional.isPresent()) {
            Coupon coupons = couponsOptional.get();
            coupons.setDeleted(true);
            coupons.setUpdateDate(LocalDate.now());
            CompletableFuture.runAsync(() -> couponsRepository.save(coupons));
        }
    }

    @Async
    public void deleteCoupon(UUID id) {
        CompletableFuture.runAsync(() -> couponsRepository.deleteById(id));
    }
}