package com.spring6framework.ChuTro.repositories;

import com.spring6framework.ChuTro.entities.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FurnitureRepository extends JpaRepository<Furniture, UUID> {
}
