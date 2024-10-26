package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.dto.request.ContractRequest;
import com.spring6framework.ChuTro.dto.request.RenewContractRequest;
import com.spring6framework.ChuTro.dto.response.ContractResponse;
import com.spring6framework.ChuTro.entities.Contract;
import com.spring6framework.ChuTro.mappers.ContractMapper;
import com.spring6framework.ChuTro.repositories.ContractRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    @Override
    public ContractResponse getContractById(UUID contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        return contractMapper.contactToContractResponse(contract);
    }

    @Override
    public ContractResponse renewContract(UUID contractId, RenewContractRequest request) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        try{
            contractMapper.updateContract(contract, request);
            contractRepository.save(contract);
        }catch (Exception exception){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }

        return contractMapper.contactToContractResponse(contract);
    }

    @Override
    public ContractResponse saveNewContact(ContractRequest request) {
        Contract contract = contractMapper.contactRequestToContract(request);
        contract.setContactId(UUID.randomUUID());

        contractRepository.save(contract);

        return contractMapper.contactToContractResponse(contract);
    }

    @Override
    public void cancelContract(UUID contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        contract.setRoom(null);

        contractRepository.delete(contract);
    }
}
