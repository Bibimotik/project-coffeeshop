package org.backend.Application.Service;

import org.backend.API.Mappers.ClientMapper;
import org.backend.Application.DTO.ClientDTO;
import org.backend.Application.Exceptions.AlreadyExistException;
import org.backend.Application.Interfaces.IClientsService;
import org.backend.Domain.Model.Client;
import org.backend.Persistence.Repository.ClientRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ClientService implements IClientsService {
	private final ClientRepository clientsRepository;

	public ClientService(ClientRepository clientsRepository) {
		this.clientsRepository = clientsRepository;
	}

	@Async
	public CompletableFuture<Iterable<Client>> getAllClients() {
		return CompletableFuture.supplyAsync(clientsRepository::findAll);
	}

	@Async
	public CompletableFuture<Optional<Client>> getClientById(UUID id) {
		return CompletableFuture.supplyAsync(() -> clientsRepository.findById(id));
	}

	@Async
	public CompletableFuture<Client> createAsync(ClientDTO clientDTO) {
		if (clientsRepository.existsByEmail(clientDTO.email()))
			throw new AlreadyExistException("Client with email " + clientDTO.email() + " already exists.");

		var client = ClientMapper.toEntity(clientDTO);

		return CompletableFuture.completedFuture(clientsRepository.save(client));
	}

	@Async
	public CompletableFuture<Client> updateClient(UUID id, Client clients) {
		Optional<Client> existingClient = clientsRepository.findById(id);

		if (existingClient.isPresent()) {
			Client existing = existingClient.get();

			existing.setName(clients.getName());
			existing.setEmail(clients.getEmail());
			existing.setDateOfBirth(clients.getDateOfBirth());
			existing.setUpdateDate(LocalDate.now());
		}
		else {
			clients.setCreationDate(LocalDate.now());
			clients.setUpdateDate(LocalDate.now());
		}

		return CompletableFuture.supplyAsync(() -> clientsRepository.save(clients));
	}

	@Async
	public void stopAccount(UUID id) {
		Optional<Client> clientOptional = clientsRepository.findById(id);

		if (clientOptional.isPresent()) {
			Client client = clientOptional.get();
			client.setDeleted(true);
			client.setUpdateDate(LocalDate.now());
			CompletableFuture.runAsync(() -> clientsRepository.save(client));
		}
	}

	@Async
	public void deleteClient(UUID id) {
		CompletableFuture.runAsync(() -> clientsRepository.deleteById(id));
	}
}
