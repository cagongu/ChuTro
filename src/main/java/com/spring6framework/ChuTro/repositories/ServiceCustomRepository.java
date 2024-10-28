package com.spring6framework.ChuTro.repositories;

import com.spring6framework.ChuTro.entities.ServiceCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceCustomRepository extends JpaRepository<ServiceCustom, UUID> {
}
