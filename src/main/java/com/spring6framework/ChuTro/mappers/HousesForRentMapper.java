package com.spring6framework.ChuTro.mappers;

import com.spring6framework.ChuTro.dto.request.HousesForRentCreationRequest;
import com.spring6framework.ChuTro.dto.request.HousesForRentUpdateRequest;
import com.spring6framework.ChuTro.dto.response.HousesForRentResponse;
import com.spring6framework.ChuTro.entities.HousesForRent;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HousesForRentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    HousesForRent dormitoryCreationRequestToDormitory(HousesForRentCreationRequest request);

    HousesForRentResponse dormitoryToDormitoryResponse(HousesForRent housesRorRent);

    @Mapping(target = "id", ignore = true)  // Không cập nhật id
    @Mapping(target = "rooms", ignore = true)  // Không cập nhật rooms
    @Mapping(target = "name", expression = "java(request.getName() != null ? request.getName() : housesForRent.getName())")
    @Mapping(target = "typeOfRental", expression = "java(request.getTypeOfRental() != null ? request.getTypeOfRental() : housesForRent.getTypeOfRental())")
    @Mapping(target = "totalFloors", expression = "java(request.getTotalFloors() != 0 ? request.getTotalFloors() : housesForRent.getTotalFloors())")
    @Mapping(target = "totalRooms", expression = "java(request.getTotalRooms() != 0 ? request.getTotalRooms() : housesForRent.getTotalRooms())")
    @Mapping(target = "address", expression = "java(request.getAddress() != null ? request.getAddress() : housesForRent.getAddress())")
    @Mapping(target = "activeStatus", expression = "java(request.getActiveStatus() != null ? request.getActiveStatus() : housesForRent.getActiveStatus())")
    @Mapping(target = "services", expression = "java(request.getServices() != null ? request.getServices() : housesForRent.getServices())")
    void updateDormitory(@MappingTarget HousesForRent housesForRent, HousesForRentUpdateRequest request);
}
