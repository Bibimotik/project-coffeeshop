package org.backend.controller;

import org.backend.model.Clients;
import org.backend.service.ClientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/clients")
public class ClientsController {
    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping
    public Iterable<Clients> getAllClients() {
        return clientsService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clients> getClientById(@PathVariable UUID id) {
        Optional<Clients> client = clientsService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Clients createClient(@RequestBody Clients clients) {
        return clientsService.createClient(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clients> updateClient(@PathVariable UUID id, @RequestBody Clients clients) {
        Clients updatedClient = clientsService.updateClient(id, clients);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/stop/{id}")
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