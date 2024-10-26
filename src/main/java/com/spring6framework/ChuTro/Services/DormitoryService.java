package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.dto.request.HousesForRentCreationRequest;
import com.spring6framework.ChuTro.dto.request.HousesForRentUpdateRequest;
import com.spring6framework.ChuTro.dto.response.HousesForRentResponse;

import java.util.List;
import java.util.UUID;

public interface DormitoryService {
    List<HousesForRentResponse> getAll();
    HousesForRentResponse getById(UUID uuid);
    void updateHousesForRentById(UUID uuid, HousesForRentUpdateRequest request);

    HousesForRentResponse createNewDormitory(HousesForRentCreationRequest request);

    void deleteById (UUID uuid);
}
