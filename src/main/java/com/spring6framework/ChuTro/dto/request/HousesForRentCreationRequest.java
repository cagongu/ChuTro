package com.spring6framework.ChuTro.dto.request;

import com.spring6framework.ChuTro.entities.Service;
import com.spring6framework.ChuTro.enums.ActiveStatus;
import com.spring6framework.ChuTro.entities.Address;
import com.spring6framework.ChuTro.enums.TypeOfRental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HousesForRentCreationRequest {
    private String name;
    private TypeOfRental typeOfRental;
    private int totalFloors;
    private int totalRooms;
    private String ownerId;

    private Address address;
    private ActiveStatus activeStatus;
    private Set<Service> services;
}
