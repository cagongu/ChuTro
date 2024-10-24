package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.dto.request.RoomCreationRequest;
import com.spring6framework.ChuTro.dto.request.RoomUpdateRequest;
import com.spring6framework.ChuTro.dto.response.RoomResponse;
import com.spring6framework.ChuTro.enums.RoomStatus;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface RoomService {
    Optional<RoomResponse> getRoomById(UUID id);
    Page<RoomResponse> getAll(String roomName, Integer pageNumber, Integer pageSize);
    Page<RoomResponse> getAllByRoomStatus(RoomStatus roomStatus);
    RoomResponse saveRoom(RoomCreationRequest request);
    Optional<RoomResponse> updateRoomById(UUID id, RoomUpdateRequest request);
    void deleteRoomById(UUID id);
}
