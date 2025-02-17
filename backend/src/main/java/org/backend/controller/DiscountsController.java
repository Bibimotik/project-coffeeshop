package org.backend.controller;

import org.backend.model.Discounts;
import org.backend.service.DiscountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin/discounts")
public class DiscountsController {
    private final DiscountsService discountsService;

    public DiscountsController(DiscountsService discountsService) {
        this.discountsService = discountsService;
    }

    @GetMapping
    public Iterable<Discounts> getDiscounts() {
        return discountsService.getAllDiscounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discounts> getDiscountById(@PathVariable int id) {
        Optional<Discounts> discount = discountsService.getDiscountById(id);
        return discount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Discounts createDiscount(@RequestBody Discounts discounts) {
        return discountsService.createDiscount(discounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discounts> updateDiscount(@PathVariable int id, @RequestBody Discounts discounts) {
        Discounts updatedDiscount = discountsService.updateDiscount(id, discounts);
        return new ResponseEntity<>(updatedDiscount, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable int id) {
        discountsService.deleteDiscount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
