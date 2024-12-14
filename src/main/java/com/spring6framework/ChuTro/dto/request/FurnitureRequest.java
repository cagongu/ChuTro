package com.spring6framework.ChuTro.dto.request;

import com.spring6framework.ChuTro.enums.UnitOfMeasurement;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FurnitureRequest {
    private String name;
    private String iconUrl;
    private double assetValue; //gia tri cua tai san
    private int totalNumber;
    private UnitOfMeasurement unitOfMeasurement;
}
