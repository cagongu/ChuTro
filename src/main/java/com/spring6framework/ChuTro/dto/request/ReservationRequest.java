package com.spring6framework.ChuTro.dto.request;

import com.spring6framework.ChuTro.enums.ActiveStatus;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

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
    private ActiveStatus status;
    private boolean current;
    private String notes;
    private Timestamp createdDate;
}
