package org.backend.Application.Interfaces;

import org.backend.Application.DTO.DiscountDTO;
import org.backend.Domain.Model.Discount;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IDiscountsService {
    CompletableFuture<Iterable<Discount>> getAllDiscounts();
    CompletableFuture<Optional<Discount>> getDiscountById(UUID id);
    CompletableFuture<Discount> createDiscount(DiscountDTO discountDTO);
    CompletableFuture<Discount> updateDiscount(UUID id, Discount discounts);
    void stopDiscount(UUID id);
    void deleteDiscount(UUID id);
}
