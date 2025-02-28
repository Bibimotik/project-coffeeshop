package org.backend.Application.Service;

import org.backend.API.Mappers.ClientMapper;
import org.backend.Application.DTO.ClientDTO;
import org.backend.Application.Interfaces.IClientsService;
import org.backend.Domain.Model.Client;
import org.backend.Persistence.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService implements IClientsService {
    private final ClientRepository clientsRepository;

    public ClientService(ClientRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public Iterable<Client> getAllClients() {
        return clientsRepository.findAll();
    }

    public Optional<Client> getClientById(UUID id) {
        return clientsRepository.findById(id);
    }

    public Client createClient(ClientDTO clientDTO) {
        Client client = ClientMapper.toEntity(clientDTO);
        return clientsRepository.save(client);
    }

    public Client updateClient(UUID id, Client clients) {
        Optional<Client> existingClient = clientsRepository.findById(id);

        if (existingClient.isPresent()) {
            Client existing = existingClient.get();

            existing.setName(clients.getName());
            existing.setEmail(clients.getEmail());
            existing.setDateOfBirth(clients.getDateOfBirth());
            existing.setUpdateDate(LocalDate.now());
        } else {
            clients.setCreationDate(LocalDate.now());
            clients.setUpdateDate(LocalDate.now());
        }

        return clientsRepository.save(clients);
    }

    public void stopAccount(UUID id) {
        Optional<Client> clientOptional = clientsRepository.findById(id);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setDeleted(true);
            client.setUpdateDate(LocalDate.now());
            clientsRepository.save(client);
        }
    }

    public void deleteClient(UUID id) {
        clientsRepository.deleteById(id);
    }
}
