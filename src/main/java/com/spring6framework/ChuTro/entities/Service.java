package com.spring6framework.ChuTro.entities;

import com.spring6framework.ChuTro.enums.ActiveStatus;
import com.spring6framework.ChuTro.enums.CostType;
import com.spring6framework.ChuTro.enums.UnitOfMeasurement;
import jakarta.persistence.*;
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
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID serviceId;
    private String serviceName;
    private Double servicePriceDefault;
    private long serviceMetrics; //so dien nuoc vv se duoc cap nhat o day
    private CostType costType;
    private UnitOfMeasurement unitOfMeasurement;
    private ActiveStatus activeStatus;
}
