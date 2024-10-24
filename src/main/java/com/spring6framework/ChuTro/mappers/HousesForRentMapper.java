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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    void updateDormitory(@MappingTarget HousesForRent housesRorRent, HousesForRentUpdateRequest request);
}
