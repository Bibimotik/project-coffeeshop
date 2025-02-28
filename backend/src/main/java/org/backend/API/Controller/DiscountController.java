package org.backend.API.Controller;

import org.backend.Application.DTO.DiscountDTO;
import org.backend.Domain.Model.Discount;
import org.backend.Application.Service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/discounts")
public class DiscountController {
    private final DiscountService discountsService;

    public DiscountController(DiscountService discountsService) {
        this.discountsService = discountsService;
    }

    @GetMapping
    public Iterable<Discount> getDiscounts() {
        return discountsService.getAllDiscounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable UUID id) {
        Optional<Discount> discount = discountsService.getDiscountById(id);
        return discount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Discount createDiscount(@RequestBody DiscountDTO discountDTO) {
        return discountsService.createDiscount(discountDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable UUID id, @RequestBody Discount discounts) {
        Discount updatedDiscount = discountsService.updateDiscount(id, discounts);
        return new ResponseEntity<>(updatedDiscount, HttpStatus.OK);
    }

    @PostMapping("/stop/{id}")
    public ResponseEntity<Void> stopAccount(@PathVariable UUID id) {
        discountsService.stopDiscount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable UUID id) {
        discountsService.deleteDiscount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
