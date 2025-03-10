package org.backend.API.Controller;

import jakarta.servlet.ServletRequest;
import org.backend.Application.DTO.GoodsDTO;
import org.backend.Domain.Model.Goods;
import org.backend.Application.Service.GoodsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin/goods")
public class GoodsController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<Iterable<Goods>>> getAllGoods() {
        return goodsService.getAllGoods()
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Optional<Goods>>> getGoods(@PathVariable UUID id, ServletRequest servletRequest) {
        return goodsService.getGoodsById(id)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Goods>> createGoods(@RequestBody GoodsDTO goodsDTO) {
        return goodsService.createGoods(goodsDTO)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Goods>> updateGoods(@PathVariable UUID id, @RequestBody Goods goods) {
        return goodsService.getGoodsById(id)
                .thenApply(goodsOpt -> goodsOpt.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/stop/{id}")
    public CompletableFuture<ResponseEntity<Object>> stopGoods(@PathVariable UUID id) {
        return CompletableFuture.runAsync(() -> goodsService.stopGoods(id))
                .thenApply(unused -> ResponseEntity.notFound().build())
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Object>> deleteGoods(@PathVariable UUID id) {
        return CompletableFuture.runAsync(() -> goodsService.deleteGoods(id))
                .thenApply(unused -> ResponseEntity.notFound().build())
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
