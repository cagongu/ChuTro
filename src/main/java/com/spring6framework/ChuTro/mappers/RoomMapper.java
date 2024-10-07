package com.spring6framework.ChuTro.mappers;

import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.model.RoomDTO;
import org.mapstruct.Mapper;

@Mapper
public interface RoomMapper {
    RoomDTO roomToRoomDto(Room room);
    Room roomDtoToRoom(RoomDTO roomDTO);
}
