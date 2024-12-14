package com.spring6framework.ChuTro.controller;

import com.spring6framework.ChuTro.Services.ReservationService;
import com.spring6framework.ChuTro.dto.request.ReservationRequest;
import com.spring6framework.ChuTro.dto.response.ApiResponse;
import com.spring6framework.ChuTro.dto.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private static final String RESERVATION_PATH = "/reservation";
    private static final String RESERVATION_PATH_ID = RESERVATION_PATH + "/{reservationId}";

    private final ReservationService reservationService;



    @PostMapping(RESERVATION_PATH + "/{roomId}")
    public ApiResponse<ReservationResponse> createReservation(
            @PathVariable("roomId") UUID roomId, @RequestBody ReservationRequest request) {
        ReservationResponse reservationResponse = reservationService.createReservation(roomId, request);
        return ApiResponse.<ReservationResponse>builder()
                .result(reservationResponse)
                .build();
    }

    @GetMapping(RESERVATION_PATH_ID)
    public ApiResponse<ReservationResponse> getReservationById(@PathVariable("reservationId") UUID reservationId) {
        ReservationResponse reservationResponse = reservationService.getById(reservationId);
        return ApiResponse.<ReservationResponse>builder()
                .result(reservationResponse)
                .build();
    }

    @GetMapping(RESERVATION_PATH + "/currentReservation/{roomId}")
    public ApiResponse<ReservationResponse> getCurrentReservation(@PathVariable("roomId") UUID roomId) {
        return ApiResponse.<ReservationResponse>builder()
                .result(  reservationService.getCurrentReservation(roomId))
                .build();
    }

    @DeleteMapping(RESERVATION_PATH + "/{roomId}")
    public ApiResponse<ResponseEntity<Void>> cancelReservation(@PathVariable UUID roomId) {
        reservationService.cancelReservation(roomId);
        return ApiResponse.<ResponseEntity<Void>>builder()
                .result(new ResponseEntity<>(HttpStatus.NO_CONTENT))
                .build();
    }
}