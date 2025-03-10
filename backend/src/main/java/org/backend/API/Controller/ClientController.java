package org.backend.API.Controller;

import org.backend.Application.DTO.ClientDTO;
import org.backend.Application.Interfaces.IClientsService;
import org.backend.Domain.Model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin/clients")
public class ClientController {
    private final IClientsService clientsService;

    public ClientController(IClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<Iterable<Client>>> getAllClients() {
        return clientsService.getAllClients()
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Client>> getClientById(@PathVariable UUID id) {
        return clientsService.getClientById(id)
                .thenApply(clientOpt -> clientOpt.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Client>> createClient(@RequestBody ClientDTO clientDTO) {
        return clientsService.createAsync(clientDTO)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Client>> updateClient(@PathVariable UUID id, @RequestBody Client client) {
        return clientsService.updateClient(id, client)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/stop/{id}")
    public CompletableFuture<ResponseEntity<Object>> stopAccount(@PathVariable UUID id) {
        return CompletableFuture.runAsync(() -> clientsService.stopAccount(id))
                .thenApply(unused -> ResponseEntity.ok().build())
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Object>> deleteClient(@PathVariable UUID id) {
        return CompletableFuture.runAsync(() -> clientsService.deleteClient(id))
                .thenApply(unused -> ResponseEntity.noContent().build())
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
