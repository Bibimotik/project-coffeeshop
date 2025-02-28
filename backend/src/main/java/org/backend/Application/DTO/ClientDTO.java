package org.backend.Application.DTO;

import java.time.LocalDate;

public record ClientDTO(
        String name,
        String email,
        LocalDate dateOfBirth
) {
}
