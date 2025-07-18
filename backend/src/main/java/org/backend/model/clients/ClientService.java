package org.backend.model.clients;

import jakarta.validation.Valid;
import org.backend.model.clients.DTO.ClientRequestDto;
import org.backend.model.clients.DTO.ClientResponseDto;
import org.backend.model.clients.exception.ClientAlreadyExistsException;
import org.backend.model.clients.exception.ClientNotExists;
import org.backend.model.clients.exception.ClientServiceException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ClientService implements IClientService {
  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;

  public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
    this.clientRepository = clientRepository;
    this.clientMapper = clientMapper;
  }

  @Async
  public CompletableFuture<ClientResponseDto> findClient(UUID clientId) {
    return CompletableFuture.supplyAsync(() -> {
            Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotExists("Client with ID " + clientId + " not found"));
            return clientMapper.toDTO(client);
    }).exceptionally(ex -> {
            Throwable cause = ex.getCause();
            if (ex instanceof ClientNotExists) {
              throw (ClientNotExists) cause;
            }
            throw new ClientServiceException("Error finding client", cause);
    });
  }

  @Async
  public CompletableFuture<ClientResponseDto> addClient(@Valid ClientRequestDto clientRequestDto) {
    return CompletableFuture.supplyAsync(() -> {
            if (clientRepository.existsClientsByEmailOrPhone(clientRequestDto.email(), clientRequestDto.phone())) {
              throw new ClientAlreadyExistsException("Client with this email or phone already exists");
            }
            Client client = clientMapper.toEntity(clientRequestDto);
            Client savedClient = clientRepository.save(client);
            return clientMapper.toDTO(savedClient);
    }).exceptionally(ex -> {
            Throwable cause = ex.getCause();
            if (ex.getCause() instanceof ClientAlreadyExistsException) {
              throw (ClientAlreadyExistsException) cause;
            }
            throw new ClientServiceException("Failed to save client: " + ex.getMessage(), cause);
    });
  }

  @Async
  public CompletableFuture<ClientResponseDto> updateClient(UUID clientId, @Valid ClientRequestDto clientRequestDto) {
    return CompletableFuture.supplyAsync(() -> {
            Client existingClient = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotExists("Client with ID " + clientId + " not found"));

            if (clientRepository.existsClientsByEmailOrPhone(clientRequestDto.email(), clientRequestDto.phone())) {
              throw new ClientAlreadyExistsException("Client with this email or phone already exists");
            }

            existingClient.setEmail(clientRequestDto.email());
            existingClient.setPhone(clientRequestDto.phone());
            existingClient.setFullName(clientRequestDto.fullName());

            Client updatedClient = clientRepository.save(existingClient);
            return clientMapper.toDTO(updatedClient);
    }).exceptionally(ex -> {
            Throwable cause = ex.getCause();
            if (ex.getCause() instanceof ClientNotExists) {
              throw (ClientNotExists) cause;
            }
            throw new ClientServiceException("Failed to update client: " + ex.getMessage(), cause);
    });
  }

  @Async
  public CompletableFuture<Void> deleteClient(UUID clientId) {
    return CompletableFuture.runAsync(() -> {
      if (!clientRepository.existsById(clientId)) {
        throw new ClientNotExists("Client with ID " + clientId + " not found");
      }
      clientRepository.deleteById(clientId);
    }).exceptionally(ex -> {
      Throwable cause = ex.getCause();
      if (ex.getCause() instanceof ClientNotExists) {
        throw (ClientNotExists) cause;
      }
      throw new ClientServiceException("Failed to delete client: " + ex.getMessage(), cause);
    });
  }
}
