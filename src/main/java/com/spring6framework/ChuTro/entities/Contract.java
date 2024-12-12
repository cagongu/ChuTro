package com.spring6framework.ChuTro.entities;

import com.spring6framework.ChuTro.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
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
    private Boolean current;
    @ManyToOne
    private Room room;
    private Timestamp paymentDate;
    private String notes;
}
