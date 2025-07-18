package org.backend.model.clients;

import org.backend.model.clients.DTO.ClientRequestDto;
import org.backend.model.clients.DTO.ClientResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
  private final IClientService clientsService;
  public ClientController(IClientService clientsService) {
    this.clientsService = clientsService;
  }

  @PostMapping()
  public ResponseEntity<CompletableFuture<ClientResponseDto>> addClient(@RequestBody ClientRequestDto client) {
    return ResponseEntity.ok().body(clientsService.addClient(client));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CompletableFuture<ClientResponseDto>> getClientById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok().body(clientsService.findClient(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CompletableFuture<ClientResponseDto>> updateClient(@PathVariable("id") UUID id, @RequestBody ClientRequestDto client) {
    return ResponseEntity.ok().body(clientsService.updateClient(id, client));
  }

  @DeleteMapping("/{id}")
  public CompletableFuture<ResponseEntity<Void>> deleteClient(@PathVariable("id") UUID id) {
    return clientsService.deleteClient(id)
            .thenApply(unused -> ResponseEntity.noContent().build());
  }
}
