package com.spring6framework.ChuTro.dto.request;

import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.enums.Gender;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {

    private int contactDuration;
    private Timestamp moveInDate;
    private Timestamp terminationDate;

    private int memberCount;
    private String tenantName;
    private String phoneNumber;
    private String idCard;
    private Timestamp dob;
    private Gender gender;

    private Room room;

    private Timestamp paymentDate;
    private String notes;
}
