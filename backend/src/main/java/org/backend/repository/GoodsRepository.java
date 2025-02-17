package org.backend.repository;

import org.backend.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoodsRepository extends JpaRepository<Goods, UUID> {
}
