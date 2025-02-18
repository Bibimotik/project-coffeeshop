package org.backend.Application.Service;

import org.backend.Domain.Interfaces.IGoodsService;
import org.backend.Domain.Model.Goods;
import org.backend.Persistence.Repository.GoodsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class GoodsService implements IGoodsService {
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
//        goods.setCreationDate(LocalDate.now());
//        goods.setUpdateDate(LocalDate.now());
        return goodsRepository.save(goods);
    }

    public Goods updateGoods(UUID id, Goods goods) {
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

        return goodsRepository.save(goods);
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

    public void deleteGoods(UUID id) {
        goodsRepository.deleteById(id);
    }
}
