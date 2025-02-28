package org.backend.Persistence.Repository;

import org.backend.Domain.Model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoodsRepository extends JpaRepository<Goods, UUID> {
}
