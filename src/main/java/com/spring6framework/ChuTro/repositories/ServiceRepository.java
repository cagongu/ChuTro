package com.spring6framework.ChuTro.repositories;

import com.spring6framework.ChuTro.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
}
