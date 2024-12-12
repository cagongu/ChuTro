package com.spring6framework.ChuTro.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring6framework.ChuTro.entities.*;
import com.spring6framework.ChuTro.enums.FinancialStatus;
import com.spring6framework.ChuTro.enums.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomCreationRequest {
    private Long roomNumber;
    private String roomName;
    private Long floorNumber;
    private Double area;
    private Double rentalPrice;
    private Double depositAmount;
    private Double debtAmount;
    private int invoiceDate;
    private int billingCycle;
    private Timestamp moveInDate;
    private int contractDuration;
    private FinancialStatus financialStatus;

    private RoomStatus status;

    private int maxOccupants;

    private UUID housesForRentId;
    private Set<ServiceCustom> serviceCustoms;

    private Set<Furniture> furnitures;
//    private Reservation reservation;

    @JsonIgnore
    private Set<Invoice> invoices;
    @JsonIgnore
    private Set<Contract> contracts;
}