package com.spring6framework.ChuTro.entities;

import com.spring6framework.ChuTro.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.mapping.ToOne;
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

    //    Thoi han hop dong
    private int contactDuration;
    private Timestamp moveInDate;
    private Timestamp terminationDate;

    private LocalDate renewalDate;
    private LocalDate renewalEndDate;

    private int memberCount;
    private String tenantName;
    private String phoneNumber;
    private String idCard;
    private Timestamp dob;
    private Gender gender;

    private Boolean isCurrent;

    @ManyToOne
    private Room room;
//    thong tin su dung dich vu
//    room.getService -> chinh sua cac chi so hien tai va tick de enable

    //    thong tin gia tri hop dong
//    room.depositAmount -> so tien thuc thu se bang deposit - debtAmount
//    room.rentalPrice
//    room.billingCycle
    private Timestamp paymentDate;
    private String notes;

//    tai san cua phong
//    room.furniture -> cai nao app dung thi tick, khong tich mac dinh disable
}
