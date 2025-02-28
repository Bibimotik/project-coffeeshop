package org.backend.Application.Interfaces;

import org.backend.Application.DTO.DiscountDTO;
import org.backend.Domain.Model.Discount;

import java.util.Optional;
import java.util.UUID;

public interface IDiscountsService {
    Iterable<Discount> getAllDiscounts();
    Optional<Discount> getDiscountById(UUID id);
    Discount createDiscount(DiscountDTO discountDTO);
    Discount updateDiscount(UUID id, Discount discounts);
    void stopDiscount(UUID id);
    void deleteDiscount(UUID id);
}
