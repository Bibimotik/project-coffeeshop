package org.backend.Application.Interfaces;

import org.backend.Application.DTO.GoodsDTO;
import org.backend.Domain.Model.Goods;

import java.util.UUID;

public interface IGoodsService {
    Iterable<Goods> getAllGoods();
    Goods createGoods(GoodsDTO goodsDTO);
    Goods updateGoods(UUID id, Goods goods);
    void stopGoods(UUID id);
    void deleteGoods(UUID id);
}
