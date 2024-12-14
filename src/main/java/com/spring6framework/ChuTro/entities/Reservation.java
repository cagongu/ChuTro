package com.spring6framework.ChuTro.entities;

import com.spring6framework.ChuTro.enums.ActiveStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID reservationId;
    private String description;
    private String tenantName;
    private String contactNumber;
    private Timestamp reservationDate;
    private double depositAmount;
    private Timestamp moveInDate;
    private ActiveStatus status;
    @Builder.Default
    private boolean current = true;
    private String notes;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
}
