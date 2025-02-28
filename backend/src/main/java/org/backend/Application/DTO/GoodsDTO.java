package org.backend.Application.DTO;

import java.math.BigDecimal;

public record GoodsDTO(
        String category,
        String name,
        Integer size,
        String composition,
        BigDecimal price,
        String image
) {
}
