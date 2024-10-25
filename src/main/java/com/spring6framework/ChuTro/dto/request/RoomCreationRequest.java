package com.spring6framework.ChuTro.dto.request;

import com.spring6framework.ChuTro.entities.Furniture;
import com.spring6framework.ChuTro.entities.Service;
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
    private Timestamp contractDuration;
    private FinancialStatus financialStatus;

    private RoomStatus status;
    private double electricityDefault;
    private double waterDefault;
    private int maxOccupants;

    private UUID housesForRentId;
    private Set<Service> services;

    private Set<Furniture> furnitures;
}