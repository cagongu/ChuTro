package com.spring6framework.ChuTro.mappers;

import com.spring6framework.ChuTro.dto.request.ServiceRequest;
import com.spring6framework.ChuTro.dto.response.ServiceResponse;
import com.spring6framework.ChuTro.entities.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServiceMapper {
    @Mapping(ignore = true, target = "serviceId")
    Service serviceRequestToService(ServiceRequest request);

    ServiceRequest serviceToServiceRequest(Service service);

    ServiceResponse serviceToServiceResponse(Service service);

    @Mapping(target = "serviceId", ignore = true)
    void updateService(@MappingTarget Service service, ServiceRequest request);
}
