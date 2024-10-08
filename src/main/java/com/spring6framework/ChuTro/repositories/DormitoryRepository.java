package com.spring6framework.ChuTro.repositories;

import com.spring6framework.ChuTro.entities.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DormitoryRepository extends JpaRepository<Dormitory, UUID> {
}