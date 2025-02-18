package org.backend.Persistence.Repository;

import org.backend.Domain.Model.Goods;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GoodsRepository extends CrudRepository<Goods, UUID> {
}
