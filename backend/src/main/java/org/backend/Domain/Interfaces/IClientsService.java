package org.backend.Domain.Interfaces;

import org.backend.Domain.Model.Client;

import java.util.Optional;
import java.util.UUID;

public interface IClientsService {
    Iterable<Client> getAllClients();
    Optional<Client> getClientById(UUID id);
    Client createClient(Client clients);
    Client updateClient(UUID id, Client clients);
    void stopAccount(UUID id);
    void deleteClient(UUID id);
}
