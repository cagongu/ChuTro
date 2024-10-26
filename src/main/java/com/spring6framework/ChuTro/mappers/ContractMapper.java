package com.spring6framework.ChuTro.mappers;

import com.spring6framework.ChuTro.dto.request.ContractRequest;
import com.spring6framework.ChuTro.dto.request.RenewContractRequest;
import com.spring6framework.ChuTro.dto.response.ContractResponse;
import com.spring6framework.ChuTro.entities.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContractMapper {
    @Mapping(target = "renewalDate", ignore = true)
    @Mapping(target = "renewalEndDate", ignore = true)
    @Mapping(target = "contactId", ignore = true)
    Contract contactRequestToContract(ContractRequest request);

    ContractResponse contactToContractResponse(Contract contract);

    @Mapping(target = "contactId", ignore = true)
    @Mapping(target = "contactDuration", ignore = true)
    @Mapping(target = "moveInDate", ignore = true)
    @Mapping(target = "terminationDate", ignore = true)
    @Mapping(target = "memberCount", ignore = true)
    @Mapping(target = "tenantName", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "idCard", ignore = true)
    @Mapping(target = "dob", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "paymentDate", ignore = true)
    @Mapping(target = "notes", ignore = true)
    void updateContract(@MappingTarget Contract contract, RenewContractRequest request);
}
