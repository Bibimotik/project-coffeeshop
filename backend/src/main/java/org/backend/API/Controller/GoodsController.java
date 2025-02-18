package org.backend.API.Controller;

import org.backend.Domain.Model.Goods;
import org.backend.Application.Service.GoodsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/goods")
public class GoodsController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    public Iterable<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goods> getGoods(@PathVariable UUID id) {
        Optional<Goods> goods = goodsService.getGoods(id);
        return goods.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Goods createGoods(@RequestBody Goods goods) {
        return goodsService.createGoods(goods);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Goods> updateGoods(@PathVariable UUID id, @RequestBody Goods goods) {
        Goods updatedGoods = goodsService.updateGoods(id, goods);
        return new ResponseEntity<>(updatedGoods, HttpStatus.OK);
    }

    @PostMapping("/stop/{id}")
    public ResponseEntity<Void> stopGoods(@PathVariable UUID id) {
        goodsService.stopGoods(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoods(@PathVariable UUID id) {
        goodsService.deleteGoods(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
