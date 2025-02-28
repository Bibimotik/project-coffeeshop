package org.backend.API.Mappers;

import org.backend.Application.DTO.ClientDTO;
import org.backend.Domain.Model.Client;

public class ClientMapper {
    public static Client toEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }

        return new Client(
                clientDTO.name(),
                clientDTO.email(),
                clientDTO.dateOfBirth()
        );
    }

    public static ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }

        return new ClientDTO(
                client.getName(),
                client.getEmail(),
                client.getDateOfBirth()
        );
    }
}
