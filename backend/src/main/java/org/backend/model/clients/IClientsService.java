package org.backend.model.clients;

import jakarta.validation.Valid;
import org.backend.model.clients.DTO.ClientsRequestDto;
import org.backend.model.clients.DTO.ClientsResponseDto;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IClientsService {
  CompletableFuture<ClientsResponseDto> findClient(UUID clientId);
  CompletableFuture<ClientsResponseDto> addClient(@Valid ClientsRequestDto clientsRequestDto);
  CompletableFuture<ClientsResponseDto> updateClient(UUID clientId, @Valid ClientsRequestDto clientsRequestDto);
  CompletableFuture<Void> deleteClient(UUID clientId);
}
