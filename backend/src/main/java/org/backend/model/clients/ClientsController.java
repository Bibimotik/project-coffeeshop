package org.backend.model.clients;

import org.backend.model.clients.DTO.ClientsRequestDto;
import org.backend.model.clients.DTO.ClientsResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {
  private final IClientsService clientsService;
  public ClientsController(IClientsService clientsService) {
    this.clientsService = clientsService;
  }

  @PostMapping()
  public ResponseEntity<CompletableFuture<ClientsResponseDto>> addClient(@RequestBody ClientsRequestDto client) {
    return ResponseEntity.ok().body(clientsService.addClient(client));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CompletableFuture<ClientsResponseDto>> getClientById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok().body(clientsService.findClient(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CompletableFuture<ClientsResponseDto>> updateClient(@PathVariable("id") UUID id, @RequestBody ClientsRequestDto client) {
    return ResponseEntity.ok().body(clientsService.updateClient(id, client));
  }

  @DeleteMapping("/{id}")
  public CompletableFuture<ResponseEntity<Void>> deleteClient(@PathVariable("id") UUID id) {
    return clientsService.deleteClient(id)
            .thenApply(unused -> ResponseEntity.noContent().build());
  }
}
