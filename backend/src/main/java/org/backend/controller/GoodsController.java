package org.backend.controller;

import org.backend.model.Goods;
import org.backend.repository.GoodsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin/goods")
public class GoodsController {
    private final GoodsRepository goodsRepository;

    public GoodsController(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @GetMapping
    public Iterable<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Goods> getGoods(@PathVariable int id) {
        return goodsRepository.findById(id);
    }

    @PostMapping
    public Goods createGoods(@RequestBody Goods goods) {
        return goodsRepository.save(goods);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Goods> updateGoods(@PathVariable int id, @RequestBody Goods goods) {
        return (goodsRepository.existsById(id)
                ? new ResponseEntity<>(goodsRepository.save(goods), HttpStatus.OK)
                : new ResponseEntity<>(goodsRepository.save(goods), HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    public void deleteGoods(@PathVariable int id) {
        goodsRepository.deleteById(id);
    }
}
