package org.backend.Application.Interfaces;

import org.backend.Application.DTO.GoodsDTO;
import org.backend.Domain.Model.Goods;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IGoodsService {
    CompletableFuture<Iterable<Goods>> getAllGoods();
    CompletableFuture<Optional<Goods>> getGoodsById(UUID id);
    CompletableFuture<Goods> createGoods(GoodsDTO goodsDTO);
    CompletableFuture<Goods> updateGoods(UUID id, Goods goods);
    void stopGoods(UUID id);
    void deleteGoods(UUID id);
}
