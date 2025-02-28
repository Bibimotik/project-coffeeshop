package org.backend.Application.Service;

import org.backend.API.Mappers.DiscountMapper;
import org.backend.Application.DTO.DiscountDTO;
import org.backend.Application.Interfaces.IDiscountsService;
import org.backend.Domain.Model.Discount;
import org.backend.Persistence.Repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiscountService implements IDiscountsService {
    private final DiscountRepository discountsRepository;

    public DiscountService(DiscountRepository discountsRepository) {
        this.discountsRepository = discountsRepository;
    }

    public Iterable<Discount> getAllDiscounts() {
        return discountsRepository.findAll();
    }

    public Optional<Discount> getDiscountById(UUID id) {
        return discountsRepository.findById(id);
    }

    public Discount createDiscount(DiscountDTO discountDTO) {
        Discount discount = DiscountMapper.toEntity(discountDTO);
        return discountsRepository.save(discount);
    }

    public Discount updateDiscount(UUID id, Discount discounts) {
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

    public void stopDiscount(UUID id) {
        Optional<Discount> discountsOptional = discountsRepository.findById(id);

        if (discountsOptional.isPresent()) {
            Discount discounts = discountsOptional.get();
            discounts.setDeleted(true);
            discounts.setUpdateDate(LocalDate.now());
            discountsRepository.save(discounts);
        }
    }

    public void deleteDiscount(UUID id) {
        discountsRepository.deleteById(id);
    }
}
