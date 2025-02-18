package org.backend.Application.Service;

import org.backend.Domain.Interfaces.IDiscountsService;
import org.backend.Domain.Model.Discount;
import org.backend.Persistence.Repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DiscountService implements IDiscountsService {
    private final DiscountRepository discountsRepository;

    public DiscountService(DiscountRepository discountsRepository) {
        this.discountsRepository = discountsRepository;
    }

    public Iterable<Discount> getAllDiscounts() {
        return discountsRepository.findAll();
    }

    public Optional<Discount> getDiscountById(int id) {
        return discountsRepository.findById(id);
    }

    public Discount createDiscount(Discount discounts) {
        return discountsRepository.save(discounts);
    }

    public Discount updateDiscount(int id, Discount discounts) {
        Optional<Discount> existingDiscount = discountsRepository.findById(id);

        if (existingDiscount.isPresent()) {
            Discount existing = existingDiscount.get();

            existing.setPercent(discounts.getPercent());
            existing.setUpdateDate(LocalDate.now());
        } else {
            discounts.setCreationDate(LocalDate.now());
            discounts.setUpdateDate(LocalDate.now());
        }

        return discountsRepository.save(discounts);
    }

    public void stopDiscount(int id) {
        Optional<Discount> discountsOptional = discountsRepository.findById(id);

        if (discountsOptional.isPresent()) {
            Discount discounts = discountsOptional.get();
            discounts.setDeleted(true);
            discounts.setUpdateDate(LocalDate.now());
            discountsRepository.save(discounts);
        }
    }

    public void deleteDiscount(int id) {
        discountsRepository.deleteById(id);
    }
}
