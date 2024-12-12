package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.dto.request.HousesForRentCreationRequest;
import com.spring6framework.ChuTro.dto.request.HousesForRentUpdateRequest;
import com.spring6framework.ChuTro.dto.response.HousesForRentResponse;
import com.spring6framework.ChuTro.entities.Address;
import com.spring6framework.ChuTro.entities.ServiceCustom;
import com.spring6framework.ChuTro.enums.ActiveStatus;
import com.spring6framework.ChuTro.entities.HousesForRent;
import com.spring6framework.ChuTro.mappers.HousesForRentMapper;
import com.spring6framework.ChuTro.repositories.AddressRepository;
import com.spring6framework.ChuTro.repositories.HousesForRentRepository;
import com.spring6framework.ChuTro.repositories.ServiceCustomRepository;
import com.spring6framework.ChuTro.repositories.ServiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DormitoryServiceImpl implements DormitoryService {
    ServiceRepository serviceRepository;
    HousesForRentRepository housesForRentRepository;
    HousesForRentMapper housesForRentMapper;
    ServiceCustomRepository serviceCustomRepository;

    @Override
    public List<HousesForRentResponse> getAll() {
        return housesForRentRepository.findAll().stream()
                .map(housesForRentMapper::dormitoryToDormitoryResponse)
                .toList();
    }

    @Override
    public HousesForRentResponse getById(UUID uuid) {
        return housesForRentMapper
                .dormitoryToDormitoryResponse(housesForRentRepository
                        .findById(uuid).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND)));
    }

    @Override
    public void updateHousesForRentById(UUID uuid, HousesForRentUpdateRequest request) {
        HousesForRent updateHousesForRent = housesForRentRepository.findById(uuid).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        housesForRentMapper.updateDormitory(updateHousesForRent, request);

        housesForRentRepository.save(updateHousesForRent);

        housesForRentMapper.dormitoryToDormitoryResponse(updateHousesForRent);
    }

    @Override
    public HousesForRentResponse createNewDormitory(HousesForRentCreationRequest request) {
        HousesForRent housesForRent = housesForRentMapper.dormitoryCreationRequestToDormitory(request);

        housesForRent.setId(UUID.randomUUID());

        Set<com.spring6framework.ChuTro.entities.Service> services = request.getServices();

        serviceRepository.saveAll(services);

        return housesForRentMapper.dormitoryToDormitoryResponse(housesForRentRepository.save(housesForRent));
    }

    @Override
    public void deleteById(UUID uuid) {
        HousesForRent housesForRent = housesForRentRepository.findById(uuid).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        housesForRent.setActiveStatus(ActiveStatus.DELETED_STATUS);

        housesForRentRepository.save(housesForRent);
    }
}
