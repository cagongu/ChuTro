package com.spring6framework.ChuTro.repositories;

import com.spring6framework.ChuTro.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BuildingRepository extends JpaRepository<Building, UUID> {
}
