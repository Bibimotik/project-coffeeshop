package org.backend.controller;

import org.backend.model.Discounts;
import org.backend.repository.DiscountsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin/discounts")
public class DiscountsController {
    private final DiscountsRepository discountsRepository;

    public DiscountsController(DiscountsRepository discountsRepository) {
        this.discountsRepository = discountsRepository;
    }

    @GetMapping
    public Iterable<Discounts> getDiscounts() {
        return discountsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Discounts> getDiscountById(@PathVariable int id) {
        return discountsRepository.findById(id);
    }

    @PostMapping
    public Discounts createDiscount(@RequestBody Discounts discounts) {
        return discountsRepository.save(discounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discounts> updateDiscount(@PathVariable int id, @RequestBody Discounts discounts) {
        return (discountsRepository.existsById(id)
                ? new ResponseEntity<>(discountsRepository.save(discounts), HttpStatus.OK)
                : new ResponseEntity<>(discountsRepository.save(discounts), HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    public void deleteDiscount(@PathVariable int id) {
        discountsRepository.deleteById(id);
    }
}
