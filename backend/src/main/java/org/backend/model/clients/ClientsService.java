package org.backend.model.clients;

import jakarta.validation.Valid;
import org.backend.model.clients.DTO.ClientsRequestDto;
import org.backend.model.clients.DTO.ClientsResponseDto;
import org.backend.model.clients.exception.ClientAlreadyExistsException;
import org.backend.model.clients.exception.ClientNotExists;
import org.backend.model.clients.exception.ClientServiceException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ClientsService implements IClientsService {
  private final ClientsRepository clientsRepository;
  private final ClientsMapper clientsMapper;

  public ClientsService(ClientsRepository clientsRepository, ClientsMapper clientsMapper) {
    this.clientsRepository = clientsRepository;
    this.clientsMapper = clientsMapper;
  }

  @Async
  public CompletableFuture<ClientsResponseDto> findClient(UUID clientId) {
    return CompletableFuture.supplyAsync(() -> {
            Clients client = clientsRepository.findById(clientId).orElseThrow(() -> new ClientNotExists("Client with ID " + clientId + " not found"));
            return clientsMapper.toDTO(client);
    }).exceptionally(ex -> {
            if (ex instanceof ClientNotExists) {
              throw (ClientNotExists) ex.getCause();
            }
            throw new ClientServiceException("Error finding client", ex);
    });
  }

  @Async
  public CompletableFuture<ClientsResponseDto> addClient(@Valid ClientsRequestDto clientsRequestDto) {
    return CompletableFuture.supplyAsync(() -> {
            if (clientsRepository.existsClientsByEmailOrPhone(clientsRequestDto.email(), clientsRequestDto.phone())) {
              throw new ClientAlreadyExistsException("Client with this email or phone already exists");
            }
            Clients client = clientsMapper.toEntity(clientsRequestDto);
            Clients savedClient = clientsRepository.save(client);
            return clientsMapper.toDTO(savedClient);
    }).exceptionally(ex -> {
            if (ex.getCause() instanceof ClientAlreadyExistsException) {
              throw (ClientAlreadyExistsException) ex.getCause();
            }
            throw new ClientServiceException("Failed to save client: " + ex.getMessage(), ex.getCause());
    });
  }

  @Async
  public CompletableFuture<ClientsResponseDto> updateClient(UUID clientId, @Valid ClientsRequestDto clientsRequestDto) {
    return CompletableFuture.supplyAsync(() -> {
            if (!clientsRepository.existsById(clientId)) {
              throw new ClientNotExists("Client with ID " + clientId + " not found");
            }

            Clients client = clientsMapper.toEntity(clientsRequestDto);
            Clients updatedClient = clientsRepository.save(client);
            return clientsMapper.toDTO(updatedClient);
    }).exceptionally(ex -> {
            if (ex.getCause() instanceof ClientNotExists) {
              throw (ClientNotExists) ex.getCause();
            }
            throw new ClientServiceException("Failed to update client: " + ex.getMessage(), ex.getCause());
    });
  }

  @Async
  public void deleteClient(UUID clientId) {
    clientsRepository.deleteById(clientId);
  }
}
