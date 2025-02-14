package org.backend.controller;

import org.backend.model.Clients;
import org.backend.repository.ClientsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/clients")
public class ClientsController {
    private final ClientsRepository clientsRepository;

    public ClientsController(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @GetMapping
    public Iterable<Clients> getAllClients() {
        return clientsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Clients> getClientById(@PathVariable UUID id) {
        return clientsRepository.findById(id);
    }

    @PostMapping
    public Clients createClient(@RequestBody Clients clients) {
        clients.setCreationDate(LocalDate.now());
        clients.setUpdateDate(LocalDate.now());
        return clientsRepository.save(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clients> updateClient(@PathVariable UUID id, @RequestBody Clients clients) {
        Optional<Clients> updatedClient = clientsRepository.findById(id);

        if (updatedClient.isPresent()) {
            Clients existingClient = updatedClient.get();

            clients.setCreationDate(existingClient.getUpdateDate());
            clients.setUpdateDate(LocalDate.now());

            return new ResponseEntity<>(clientsRepository.save(clients), HttpStatus.OK);
        } else {
            clients.setCreationDate(LocalDate.now());
            clients.setUpdateDate(LocalDate.now());
            return new ResponseEntity<>(clientsRepository.save(clients), HttpStatus.CREATED);
        }
        /*return (clientsRepository.existsById(id)
                ? new ResponseEntity<>(clientsRepository.save(clients), HttpStatus.OK)
                : new ResponseEntity<>(clientsRepository.save(clients), HttpStatus.CREATED));*/
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id) {
        clientsRepository.deleteById(id);
    }
}
