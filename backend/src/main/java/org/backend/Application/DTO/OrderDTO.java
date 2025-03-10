package org.backend.Application.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderDTO(UUID clientId, BigDecimal totalAmount) { }
