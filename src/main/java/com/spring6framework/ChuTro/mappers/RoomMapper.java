package com.spring6framework.ChuTro.mappers;

import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.model.RoomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RoomMapper {
    @Mapping(source = "building.buildingId", target = "buildingId")
    @Mapping(source = "dormitory.dormitoryId", target = "dormitoryId")
    RoomDTO roomToRoomDto(Room room);
    @Mapping(source = "buildingId", target = "building.buildingId")
    @Mapping(source = "dormitoryId", target = "dormitory.dormitoryId")
    Room roomDtoToRoom(RoomDTO roomDTO);
}
