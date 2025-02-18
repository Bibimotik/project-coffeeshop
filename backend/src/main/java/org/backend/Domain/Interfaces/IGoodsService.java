package org.backend.Domain.Interfaces;

import org.backend.Domain.Model.Goods;

import java.util.UUID;

public interface IGoodsService {
    Iterable<Goods> getAllGoods();
    Goods createGoods(Goods goods);
    Goods updateGoods(UUID id, Goods goods);
    void stopGoods(UUID id);
    void deleteGoods(UUID id);
}
