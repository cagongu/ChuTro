package com.spring6framework.ChuTro.mappers;

import com.spring6framework.ChuTro.entities.Building;
import com.spring6framework.ChuTro.model.BuildingDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BuildingMapper {
    BuildingDTO buildingToBuildingDto(Building building);
    Building buidingDtoToBuilding(BuildingMapper buildingDTO);
}
