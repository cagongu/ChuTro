package com.spring6framework.ChuTro.dto.request;

import com.spring6framework.ChuTro.enums.ActiveStatus;
import com.spring6framework.ChuTro.entities.Address;
import com.spring6framework.ChuTro.enums.TypeOfRental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HousesForRentCreationRequest {
    private String name;
    private TypeOfRental typeOfRental;
    private int totalFloors;
    private int totalRooms;
    private Address address;
    private ActiveStatus activeStatus;
}
