package com.spring6framework.ChuTro.entities;

import com.spring6framework.ChuTro.enums.UnitOfMeasurement;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Furniture {
    @Id
    private UUID furnitureId;

    private String iconUrl;
    private double assetValue; //gia tri cua tai san
    private int totalNumber;
    private UnitOfMeasurement unitOfMeasurement;
}
