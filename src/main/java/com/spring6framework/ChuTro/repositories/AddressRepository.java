package com.spring6framework.ChuTro.repositories;

import com.spring6framework.ChuTro.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
