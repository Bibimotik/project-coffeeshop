package org.backend.Domain.Interfaces;

import org.backend.Domain.Model.Discount;

import java.util.Optional;

public interface IDiscountsService {
    Iterable<Discount> getAllDiscounts();
    Optional<Discount> getDiscountById(int id);
    Discount createDiscount(Discount discounts);
    Discount updateDiscount(int id, Discount discounts);
    void stopDiscount(int id);
    void deleteDiscount(int id);
}
