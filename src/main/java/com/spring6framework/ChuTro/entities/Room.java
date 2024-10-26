package com.spring6framework.ChuTro.entities;

import com.spring6framework.ChuTro.enums.FinancialStatus;
import com.spring6framework.ChuTro.enums.RoomStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID roomId;

    private int roomNumber;
    private String roomName;
    private int floorNumber;
    private Double area;
    private Double rentalPrice;
    private Double depositAmount;
    private Double debtAmount;
    private int invoiceDate;
    private int billingCycle;
    private Timestamp moveInDate;
    private Timestamp contractDuration;
    private FinancialStatus financialStatus;

    private RoomStatus status;
    private double electricityDefault;
    private double waterDefault;
    private int maxOccupants;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private HousesForRent housesForRent;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Service> services = new HashSet<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Furniture> furnitures = new HashSet<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    private Reservation reservation;

    @OneToMany
    private Set<Invoice> invoices;
    @OneToMany
    private Set<Contract> contracts;
}
