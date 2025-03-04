package org.backend.Application.DTO;

import org.backend.Domain.Model.Client;

import java.math.BigDecimal;

public record OrderDTO(
        Client clientId,
        BigDecimal totalAmount
) {
}
