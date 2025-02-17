package org.backend.service;

import org.backend.model.Clients;
import org.backend.repository.ClientsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientsService {
    private final ClientsRepository clientsRepository;

    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public Iterable<Clients> getAllClients() {
        return clientsRepository.findAll();
    }

    public Optional<Clients> getClientById(UUID id) {
        return clientsRepository.findById(id);
    }

    public Clients createClient(Clients clients) {
        clients.setCreationDate(LocalDate.now());
        clients.setUpdateDate(LocalDate.now());
        return clientsRepository.save(clients);
    }

    public Clients updateClient(UUID id, Clients clients) {
        Optional<Clients> existingClient = clientsRepository.findById(id);

        if (existingClient.isPresent()) {
            Clients client = existingClient.get();
            clients.setCreationDate(client.getCreationDate());
            clients.setUpdateDate(LocalDate.now());
            return clientsRepository.save(clients);
        } else {
            clients.setCreationDate(LocalDate.now());
            clients.setUpdateDate(LocalDate.now());
            return clientsRepository.save(clients);
        }
    }

    public void stopAccount(UUID id) {
        Optional<Clients> clientOptional = clientsRepository.findById(id);

        if (clientOptional.isPresent()) {
            Clients client = clientOptional.get();
            client.setDeleted(true);
            client.setUpdateDate(LocalDate.now());
            clientsRepository.save(client);
        }
    }

    public void deleteClient(UUID id) {
        clientsRepository.deleteById(id);
    }
}
