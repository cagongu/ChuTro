package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.dto.request.ContractRequest;
import com.spring6framework.ChuTro.dto.request.RenewContractRequest;
import com.spring6framework.ChuTro.dto.response.ContractResponse;

import java.util.UUID;

public interface ContractService {
    ContractResponse getContractById(UUID contractId);

//    updateContract
    ContractResponse renewContract(UUID contractId, RenewContractRequest request);
    ContractResponse saveNewContact(ContractRequest request);
    void cancelContract(UUID contractId);
}
