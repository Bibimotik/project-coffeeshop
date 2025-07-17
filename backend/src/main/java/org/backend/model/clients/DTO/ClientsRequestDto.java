package org.backend.model.clients.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientsRequestDto(
        @NotNull @Email String email,
        @NotNull @Size(max = 255) String phone,
        @NotNull @Size(max = 255) String fullName
) { }
