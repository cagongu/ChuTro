package com.spring6framework.ChuTro.repositories;

import com.spring6framework.ChuTro.entities.HousesForRent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HousesForRentRepository extends JpaRepository<HousesForRent, UUID> {
}
