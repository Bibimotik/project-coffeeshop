package org.backend.model.clients.DTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public record ClientResponseDto(
        UUID id,
        String email,
        String phone,
        String fullName,
        Integer bonusPoints,
        LocalDateTime createdAt
) { }
