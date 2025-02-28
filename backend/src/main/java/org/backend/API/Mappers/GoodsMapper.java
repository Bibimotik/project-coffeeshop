package org.backend.API.Mappers;

import org.backend.Application.DTO.GoodsDTO;
import org.backend.Domain.Model.Goods;

public class GoodsMapper {
    public static Goods toEntity(GoodsDTO goodsDTO) {
        if (goodsDTO == null) {
            return null;
        }

        return new Goods(
                goodsDTO.category(),
                goodsDTO.name(),
                goodsDTO.size(),
                goodsDTO.composition(),
                goodsDTO.price(),
                goodsDTO.image()
        );
    }

    public static GoodsDTO toDTO(Goods goods) {
        if (goods == null) {
            return null;
        }

        return new GoodsDTO(
                goods.getCategory(),
                goods.getName(),
                goods.getSize(),
                goods.getComposition(),
                goods.getPrice(),
                goods.getImage()
        );
    }
}
