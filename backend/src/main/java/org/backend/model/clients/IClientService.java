package org.backend.model.clients;

import jakarta.validation.Valid;
import org.backend.model.clients.DTO.ClientRequestDto;
import org.backend.model.clients.DTO.ClientResponseDto;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IClientService {
  CompletableFuture<ClientResponseDto> findClient(UUID clientId);
  CompletableFuture<ClientResponseDto> addClient(@Valid ClientRequestDto clientRequestDto);
  CompletableFuture<ClientResponseDto> updateClient(UUID clientId, @Valid ClientRequestDto clientRequestDto);
  CompletableFuture<Void> deleteClient(UUID clientId);
}
