package org.backend.model.clients;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
  Optional<Client> findById(UUID id);
  boolean existsById(UUID id);
  boolean existsByEmail(String email);
  boolean existsByPhone(String phone);
  void deleteById(UUID id);
  default boolean existsClientsByEmailOrPhone(String email, String phone) {
    return existsByEmail(email) || existsByPhone(phone);
  };
}
