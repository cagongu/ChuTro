package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.entities.RoomType;
import com.spring6framework.ChuTro.model.RoomDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface RoomService {
    Optional<RoomDTO> getRoomById(UUID id);
    Page<RoomDTO> getAll(String roomName, RoomType roomType, Integer pageNumber, Integer pageSize);
    RoomDTO saveRoom(RoomDTO roomDTO);
    Optional<RoomDTO> updateRoomById(UUID id, RoomDTO roomDTO);
    void deleteRoomById(UUID id);
    Optional<RoomDTO> patchRoomId(UUID id, RoomDTO roomDTO);
}
