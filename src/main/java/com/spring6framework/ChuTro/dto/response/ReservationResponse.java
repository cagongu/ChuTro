package com.spring6framework.ChuTro.dto.response;

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

    private String notes;

}
