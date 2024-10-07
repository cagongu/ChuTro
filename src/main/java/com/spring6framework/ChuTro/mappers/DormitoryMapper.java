package com.spring6framework.ChuTro.mappers;

import com.spring6framework.ChuTro.entities.Dormitory;
import com.spring6framework.ChuTro.model.DormitoryDTO;
import org.mapstruct.Mapper;

@Mapper
public interface DormitoryMapper {
    DormitoryDTO dormitoryToDormitoryDto(Dormitory dormitory);
    Dormitory dormitoryDtoToDormitory(DormitoryMapper dormitoryDTO);
}
