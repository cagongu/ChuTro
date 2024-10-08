package com.spring6framework.ChuTro.repositories;

import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.entities.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    Page<Room> findAllByRoomNameIsLikeIgnoreCase(String roomNane, Pageable pageable);
    Page<Room> findAllByRoomType(RoomType roomType, Pageable pageable);
    Page<Room> findAllByRoomNameIsLikeIgnoreCaseAndRoomType(String roomName, RoomType roomType, Pageable pageable);
}
