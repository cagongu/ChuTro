package com.spring6framework.ChuTro.mappers;

import com.spring6framework.ChuTro.dto.request.FurnitureRequest;
import com.spring6framework.ChuTro.dto.response.FurnitureResponse;
import com.spring6framework.ChuTro.entities.Furniture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FurnitureMapper {
    @Mapping(ignore = true, target = "furnitureId")
    Furniture furnitureRequestToFurniture(FurnitureRequest request);

    FurnitureRequest furnitureToFurnitureRequest(Furniture furniture);

    FurnitureResponse furnitureToFurnitureResponse(Furniture furniture);

    @Mapping(target = "furnitureId", ignore = true)
    void updateFurniture(@MappingTarget Furniture furniture, FurnitureRequest request);
}
