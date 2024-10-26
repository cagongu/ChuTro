package com.spring6framework.ChuTro.mappers;

import com.spring6framework.ChuTro.dto.request.RoomCreationRequest;
import com.spring6framework.ChuTro.dto.request.RoomUpdateRequest;
import com.spring6framework.ChuTro.dto.response.RoomResponse;
import com.spring6framework.ChuTro.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {
    @Mapping(target = "roomId", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(source = "housesForRentId", target = "housesForRent.id")
    @Mapping(target = "reservation", ignore = true)
    Room roomCreationToRoom(RoomCreationRequest request);

    @Mapping(source = "housesForRent.id", target = "housesForRentId")
    RoomResponse roomToRoomResponse(Room room);


    @Mapping(target = "roomId", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "housesForRent", ignore = true)
    void updateRoom(@MappingTarget Room room, RoomUpdateRequest request);
}
