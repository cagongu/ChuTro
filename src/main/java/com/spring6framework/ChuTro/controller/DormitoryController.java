package com.spring6framework.ChuTro.controller;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.Services.DormitoryService;
import com.spring6framework.ChuTro.dto.request.HousesForRentCreationRequest;
import com.spring6framework.ChuTro.dto.request.HousesForRentUpdateRequest;
import com.spring6framework.ChuTro.dto.response.ApiResponse;
import com.spring6framework.ChuTro.dto.response.HousesForRentResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class DormitoryController {
    public static final String DORMITORY_PATH = "/dormitory";
    public static final String DORMITORY_PATH_ID = DORMITORY_PATH + "/{dormitoryId}";
    private final DormitoryService dormitoryService;

    @GetMapping(DORMITORY_PATH)
    public ApiResponse<List<HousesForRentResponse>> getAll() {
        return ApiResponse.<List<HousesForRentResponse>>builder()
                .result(dormitoryService.getAll())
                .build();
    }


    @GetMapping(DORMITORY_PATH_ID)
    public ApiResponse<HousesForRentResponse> getById(@PathVariable("dormitoryId") UUID uuid) {
        return ApiResponse.<HousesForRentResponse>builder()
                .result(dormitoryService.getById(uuid))
                .build();
    }

    @PutMapping(DORMITORY_PATH_ID)
    public ApiResponse<ResponseEntity<HousesForRentResponse>> updateById(@PathVariable("dormitoryId") UUID uuid, @RequestBody HousesForRentUpdateRequest request) {
        try {
            dormitoryService.updateHousesForRentById(uuid, request);
        } catch (AppException e) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        return ApiResponse.<ResponseEntity<HousesForRentResponse>>builder()
                .result(new ResponseEntity<>(HttpStatus.OK))
                .build();
    }

    @PostMapping(DORMITORY_PATH)
    public ApiResponse<ResponseEntity<HousesForRentResponse>> createNew(@RequestBody HousesForRentCreationRequest request) {
        HousesForRentResponse housesForRentResponse = dormitoryService.createNewDormitory(request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", DORMITORY_PATH + "/" + housesForRentResponse.getId().toString());


        return ApiResponse.<ResponseEntity<HousesForRentResponse>>builder()
                .result(new ResponseEntity<>(housesForRentResponse,headers, HttpStatus.CREATED))
                .build();
    }

    @DeleteMapping(DORMITORY_PATH_ID)
    public ApiResponse<ResponseEntity<Void>> deleteById(@PathVariable("dormitoryId") UUID uuid) {
        dormitoryService.deleteById(uuid);

        return ApiResponse.<ResponseEntity<Void>>builder()
                .result(new ResponseEntity<>(HttpStatus.NO_CONTENT))
                .build();
    }
}
