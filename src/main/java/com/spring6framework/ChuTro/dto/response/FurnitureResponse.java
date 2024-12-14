package com.spring6framework.ChuTro.dto.response;

import com.spring6framework.ChuTro.enums.UnitOfMeasurement;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FurnitureResponse {
    private UUID furnitureId;
    private String name;
    private String iconUrl;
    private double assetValue; //gia tri cua tai san
    private int totalNumber;
    private UnitOfMeasurement unitOfMeasurement;
}
