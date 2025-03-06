package org.backend.API.Controller;

import org.backend.Application.DTO.ClientDTO;
import org.backend.Application.Interfaces.IClientsService;
import org.backend.Domain.Model.Client;
import org.backend.Application.Service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admin/clients")
public class ClientController {
    private final IClientsService clientsService;

    public ClientController(IClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping
    public Iterable<Client> getAllClients() {
        return clientsService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID id) {
        Optional<Client> client = clientsService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CompletableFuture<Client> createClient(@RequestBody ClientDTO clientDTO) {
         return clientsService.createAsync(clientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable UUID id, @RequestBody Client clients) {
        Client updatedClient = clientsService.updateClient(id, clients);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @PostMapping("/stop/{id}")
    public ResponseEntity<Void> stopAccount(@PathVariable UUID id) {
        clientsService.stopAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientsService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}