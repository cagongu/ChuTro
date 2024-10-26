package com.spring6framework.ChuTro.dto.response;

import com.spring6framework.ChuTro.enums.ActiveStatus;
import com.spring6framework.ChuTro.enums.CostType;
import com.spring6framework.ChuTro.enums.UnitOfMeasurement;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
    private UUID serviceId;
    private String serviceName;
    private Double servicePriceDefault;
    private long serviceMetrics;
    private CostType costType;
    private UnitOfMeasurement unitOfMeasurement;
    private ActiveStatus activeStatus;

}
