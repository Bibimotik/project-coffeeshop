package org.backend.API.Mappers;

import org.backend.Application.DTO.CouponDTO;
import org.backend.Domain.Model.Coupon;

public class CouponMapper {
    public static Coupon toEntity(CouponDTO couponDTO) {
        if (couponDTO == null) {
            return null;
        }

        return new Coupon(
                couponDTO.clientId(),
                couponDTO.goodsId(),
                couponDTO.percent()
        );
    }

    public static CouponDTO toDTO(Coupon coupon) {
        if (coupon == null) {
            return null;
        }

        return new CouponDTO(
                coupon.getClient(),
                coupon.getGoods(),
                coupon.getPercent()
        );
    }
}
