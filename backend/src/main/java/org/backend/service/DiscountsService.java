package org.backend.service;

import org.backend.model.Discounts;
import org.backend.repository.DiscountsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscountsService {
    private final DiscountsRepository discountsRepository;

    public DiscountsService(DiscountsRepository discountsRepository) {
        this.discountsRepository = discountsRepository;
    }

    public Iterable<Discounts> getAllDiscounts() {
        return discountsRepository.findAll();
    }

    public Optional<Discounts> getDiscountById(int id) {
        return discountsRepository.findById(id);
    }

    public Discounts createDiscount(Discounts discounts) {
        return discountsRepository.save(discounts);
    }

    public Discounts updateDiscount(int id, Discounts discounts) {
        if (discountsRepository.existsById(id)) {
            return discountsRepository.save(discounts);
        } else {
            return discountsRepository.save(discounts);
        }
    }

    public void deleteDiscount(int id) {
        discountsRepository.deleteById(id);
    }
}
