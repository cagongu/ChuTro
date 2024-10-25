package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.dto.request.ServiceRequest;
import com.spring6framework.ChuTro.dto.response.ServiceResponse;
import com.spring6framework.ChuTro.mappers.ServiceMapper;
import com.spring6framework.ChuTro.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public List<ServiceResponse> getAll() {
        return serviceRepository.findAll().stream().map(serviceMapper::serviceToServiceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse getById(UUID serviceId) {
        return serviceMapper.serviceToServiceResponse(serviceRepository
                .findById(serviceId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND)));
    }

    @Override
    public ServiceResponse createService(ServiceRequest request) {
        com.spring6framework.ChuTro.entities.Service service = serviceMapper.serviceRequestToService(request);
        service.setServiceId(UUID.randomUUID());

        serviceRepository.save(service);

        return serviceMapper.serviceToServiceResponse(service);
    }

    @Override
    public ServiceResponse updateService(UUID serviceId, ServiceRequest request) {
        com.spring6framework.ChuTro.entities.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        serviceMapper.updateService(service, request);
        serviceRepository.save(service);

        return serviceMapper.serviceToServiceResponse(service);
    }

    @Override
    public void deleteServiceById(UUID uuid) {

    }
}
