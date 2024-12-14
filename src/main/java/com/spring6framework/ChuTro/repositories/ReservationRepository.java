package com.spring6framework.ChuTro.repositories;


import com.spring6framework.ChuTro.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
