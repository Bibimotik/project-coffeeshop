package org.backend.API.Controller;

import org.backend.Application.DTO.DiscountDTO;
import org.backend.Domain.Model.Discount;
import org.backend.Application.Service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin/discounts")
public class DiscountController {
    private final DiscountService discountsService;

    public DiscountController(DiscountService discountsService) {
        this.discountsService = discountsService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<Iterable<Discount>>> getDiscounts() {
        return discountsService.getAllDiscounts()
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Discount>> getDiscountById(@PathVariable UUID id) {
        return discountsService.getDiscountById(id)
                .thenApply(discountOpt -> discountOpt.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Discount>> createDiscount(@RequestBody DiscountDTO discountDTO) {
        return discountsService.createDiscount(discountDTO)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Discount>> updateDiscount(@PathVariable UUID id, @RequestBody Discount discount) {
        return discountsService.updateDiscount(id, discount)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/stop/{id}")
    public CompletableFuture<ResponseEntity<Object>> stopAccount(@PathVariable UUID id) {
        return CompletableFuture.runAsync(() -> discountsService.stopDiscount(id))
                .thenApply(unused -> ResponseEntity.notFound().build())
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Object>> deleteDiscount(@PathVariable UUID id) {
        return CompletableFuture.runAsync(() -> discountsService.deleteDiscount(id))
                .thenApply(unused -> ResponseEntity.notFound().build())
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
