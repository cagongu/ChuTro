package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.dto.request.ServiceRequest;
import com.spring6framework.ChuTro.dto.response.ServiceResponse;

import java.util.List;
import java.util.UUID;

@Deprecated
public interface ServiceService {
    List<ServiceResponse> getAll();
    ServiceResponse getById(UUID serviceId);
    ServiceResponse createService(ServiceRequest request);
    ServiceResponse updateService(UUID serviceId, ServiceRequest request);
    void deleteServiceById(UUID uuid);

}
