package org.backend.Application.DTO;

import org.backend.Domain.Model.Client;
import org.backend.Domain.Model.Goods;

public record CouponDTO(
        Client clientId,
        Goods goodsId,
        int percent
) {
}
