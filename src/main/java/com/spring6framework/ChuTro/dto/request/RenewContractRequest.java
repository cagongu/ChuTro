package com.spring6framework.ChuTro.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RenewContractRequest {
    private LocalDate renewalDate;
    private LocalDate renewalEndDate;
    private double rentPrice;
}
