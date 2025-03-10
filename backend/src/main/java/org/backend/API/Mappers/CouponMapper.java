package org.backend.API.Mappers;

import org.backend.Application.DTO.CouponDTO;
import org.backend.Domain.Model.Client;
import org.backend.Domain.Model.Coupon;
import org.backend.Domain.Model.Goods;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {

    public Coupon toEntity(Client client, Goods goods, CouponDTO couponDTO) {
        if (couponDTO == null) {
            return null;
        }

        return new Coupon(client, goods, couponDTO.percent());
    }

    public CouponDTO toDTO(Coupon coupon) {
        if (coupon == null) {
            return null;
        }

        return new CouponDTO(
                coupon.getClient().getId(),
                coupon.getGoods().getId(),
                coupon.getPercent()
        );
    }
}
