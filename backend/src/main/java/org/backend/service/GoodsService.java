package org.backend.service;

import org.backend.model.Goods;
import org.backend.repository.GoodsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public Iterable<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    public Optional<Goods> getGoods(UUID id) {
        return goodsRepository.findById(id);
    }

    public Goods createGoods(Goods goods) {
        goods.setCreationDate(LocalDate.now());
        goods.setUpdateDate(LocalDate.now());
        return goodsRepository.save(goods);
    }

    public Goods updateGoods(UUID id, Goods goods) {
        Optional<Goods> existingGoods = goodsRepository.findById(id);

        if (existingGoods.isPresent()) {
            Goods existing = existingGoods.get();
            goods.setCreationDate(existing.getCreationDate());
            goods.setUpdateDate(LocalDate.now());
            return goodsRepository.save(goods);
        } else {
            goods.setCreationDate(LocalDate.now());
            goods.setUpdateDate(LocalDate.now());
            return goodsRepository.save(goods);
        }
    }

    public void deleteGoods(UUID id) {
        goodsRepository.deleteById(id);
    }

    public void stopGoods(UUID id) {
        Optional<Goods> goodsOptional = goodsRepository.findById(id);

        if (goodsOptional.isPresent()) {
            Goods goods = goodsOptional.get();
            goods.setDeleted(true);
            goods.setUpdateDate(LocalDate.now());
            goodsRepository.save(goods);
        }
    }
}
