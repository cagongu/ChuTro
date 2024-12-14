package com.spring6framework.ChuTro.dto.request;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RenewContractRequest {
    private Timestamp renewalDate;
    private Timestamp renewalEndDate;
    private double rentPrice;
}
