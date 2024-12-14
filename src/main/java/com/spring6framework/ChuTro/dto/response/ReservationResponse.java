package com.spring6framework.ChuTro.dto.response;

import com.spring6framework.ChuTro.enums.ActiveStatus;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private UUID reservationId;
    private String description;
    private String tenantName;
    private String contactNumber;
    private Timestamp reservationDate;
    private double depositAmount;
    private Timestamp moveInDate;
    private ActiveStatus status;
    private boolean current;
    private String notes;
    private Timestamp createdDate;
}
