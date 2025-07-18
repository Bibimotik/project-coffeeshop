package org.backend.model.clients;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientsRepository extends JpaRepository<Clients, UUID> {
  Optional<Clients> findById(UUID id);
  boolean existsById(UUID id);
  boolean existsByEmail(String email);
  boolean existsByPhone(String phone);
  void deleteById(UUID id);
  default boolean existsClientsByEmailOrPhone(String email, String phone) {
    return existsByEmail(email) || existsByPhone(phone);
  };
}
