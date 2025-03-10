package org.backend.Application.Service;

import org.backend.API.Mappers.GoodsMapper;
import org.backend.Application.DTO.GoodsDTO;
import org.backend.Application.Interfaces.IGoodsService;
import org.backend.Domain.Model.Goods;
import org.backend.Persistence.Repository.GoodsRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class GoodsService implements IGoodsService {
    private final GoodsRepository goodsRepository;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Async
    public CompletableFuture<Iterable<Goods>> getAllGoods() {
        return CompletableFuture.supplyAsync(goodsRepository::findAll);
    }

    @Async
    public CompletableFuture<Optional<Goods>> getGoodsById(UUID id) {
        return CompletableFuture.supplyAsync(() -> goodsRepository.findById(id));
    }

    @Async
    public CompletableFuture<Goods> createGoods(GoodsDTO goodsDTO) {
        Goods goods = GoodsMapper.toEntity(goodsDTO);
        return CompletableFuture.supplyAsync(() -> goodsRepository.save(goods));
    }

    @Async
    public CompletableFuture<Goods> updateGoods(UUID id, Goods goods) {
        Optional<Goods> existingGoods = goodsRepository.findById(id);

        if (existingGoods.isPresent()) {
            Goods existing = existingGoods.get();
            existing.setCategory(existing.getCategory());
            existing.setName(existing.getName());
            existing.setSize(existing.getSize());
            existing.setComposition(existing.getComposition());
            existing.setImage(existing.getImage());
            existing.setPrice(existing.getPrice());
            existing.setUpdateDate(LocalDate.now());
        } else {
            goods.setCreationDate(LocalDate.now());
            goods.setUpdateDate(LocalDate.now());
        }

        return CompletableFuture.supplyAsync(() -> goodsRepository.save(goods));
    }

    @Async
    public void stopGoods(UUID id) {
        Optional<Goods> goodsOptional = goodsRepository.findById(id);

        if (goodsOptional.isPresent()) {
            Goods goods = goodsOptional.get();
            goods.setDeleted(true);
            goods.setUpdateDate(LocalDate.now());
            CompletableFuture.runAsync(() -> goodsRepository.save(goods));
        }
    }

    @Async
    public void deleteGoods(UUID id) {
        CompletableFuture.runAsync(() -> goodsRepository.deleteById(id));
    }
}
