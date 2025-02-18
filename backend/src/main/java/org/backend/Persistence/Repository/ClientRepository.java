package org.backend.Persistence.Repository;

import org.backend.Domain.Model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {
}
