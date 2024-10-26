package com.spring6framework.ChuTro.dto.request;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private String description;

    private String tenantName;
    private String contactNumber;

    private Timestamp reservationDate;
    private double depositAmount;
    private Timestamp moveInDate;

    private String notes;

}
