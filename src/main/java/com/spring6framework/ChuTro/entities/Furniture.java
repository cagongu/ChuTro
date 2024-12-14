package com.spring6framework.ChuTro.entities;

import com.spring6framework.ChuTro.enums.UnitOfMeasurement;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID furnitureId;
    private String name;
    private String iconUrl;
    private double assetValue; //gia tri cua tai san
    private int totalNumber;
    private UnitOfMeasurement unitOfMeasurement;
}
