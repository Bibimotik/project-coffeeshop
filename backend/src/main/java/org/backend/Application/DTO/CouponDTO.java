package org.backend.Application.DTO;

import java.util.UUID;

public record CouponDTO(
        UUID clientId,
        UUID goodsId,
        int percent
) {
}
