package com.spring6framework.ChuTro.dto.request;

import com.spring6framework.ChuTro.enums.CostType;
import com.spring6framework.ChuTro.enums.UnitOfMeasurement;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {
    private String serviceName;
    private Double servicePriceDefault;
    private long serviceMetrics;
    private CostType costType;
    private UnitOfMeasurement unitOfMeasurement;
}
