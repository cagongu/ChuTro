package com.spring6framework.ChuTro.controller;

import com.spring6framework.ChuTro.Services.ContractService;
import com.spring6framework.ChuTro.dto.request.ContractRequest;
import com.spring6framework.ChuTro.dto.request.RenewContractRequest;
import com.spring6framework.ChuTro.dto.response.ApiResponse;
import com.spring6framework.ChuTro.dto.response.ContractResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContractController {
    private static final String CONTRACT_PATH = "/contract";
    private static final String CONTRACT_PATH_ID = CONTRACT_PATH + "/{contractId}";

    private final ContractService contractService;

    @GetMapping(CONTRACT_PATH_ID)
    public ApiResponse<ContractResponse> getContractById(@PathVariable("contractId")UUID contractId){
        ContractResponse contractResponse = contractService.getContractById(contractId);

        return ApiResponse.<ContractResponse>builder()
                .result(contractResponse)
                .build();
    }

    @PutMapping(CONTRACT_PATH_ID)
    public ApiResponse<ContractResponse> renewContract(@PathVariable("contractId") UUID contractId, @RequestBody RenewContractRequest request){
        return ApiResponse.<ContractResponse>builder()
                .result(contractService.renewContract(contractId, request))
                .build();
    }

    @PostMapping(CONTRACT_PATH)
    public ApiResponse<ResponseEntity<ContractResponse>> saveNewContract(@RequestBody ContractRequest request){
        ContractResponse response = contractService.saveNewContact(request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", CONTRACT_PATH + "/" + response.getContactId().toString());

        return ApiResponse.<ResponseEntity<ContractResponse>>builder()
                .result(new ResponseEntity<>(headers, HttpStatus.CREATED))
                .build();
    }

    @DeleteMapping(CONTRACT_PATH_ID)
    public ApiResponse<Void> cancelContract(@PathVariable("contractId") UUID contractId){
        contractService.cancelContract(contractId);

        return ApiResponse.<Void>builder()
                .message("Contract have removed").build();
    }
}
