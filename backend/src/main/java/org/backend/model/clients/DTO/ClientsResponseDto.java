package org.backend.model.clients.DTO;

import java.sql.Timestamp;

public record ClientsResponseDto(
        String email,
        String phone,
        String fullName,
        int bonusPoints,
        Timestamp createdAt
) { }
