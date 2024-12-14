package com.spring6framework.ChuTro.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring6framework.ChuTro.entities.Contract;
import com.spring6framework.ChuTro.entities.Invoice;
import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.enums.Gender;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponse {
    private UUID contactId;

    private int contactDuration;
    private Timestamp moveInDate;
    private Timestamp terminationDate;

    private Timestamp renewalDate;
    private Timestamp renewalEndDate;

    private int memberCount;
    private String tenantName;
    private String phoneNumber;
    private String idCard;
    private Timestamp dob;
    private Gender gender;

    @JsonIgnore
    private Room room;
    private Timestamp paymentDate;
    private String notes;

    private Boolean current;

}
