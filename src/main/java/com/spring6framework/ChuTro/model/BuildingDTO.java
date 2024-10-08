package com.spring6framework.ChuTro.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Builder
@Data
public class BuildingDTO {
    private UUID buildingId;
    private String buildingName;
    private int numberOfFloors;
}
