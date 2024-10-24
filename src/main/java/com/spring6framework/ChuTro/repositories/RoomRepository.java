package com.spring6framework.ChuTro.repositories;

import com.spring6framework.ChuTro.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    Page<Room> findAllByRoomNameIsLikeIgnoreCase(String roomNane, Pageable pageable);
}
