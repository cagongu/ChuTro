package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.dto.request.ReservationRequest;
import com.spring6framework.ChuTro.dto.response.ReservationResponse;

import java.util.UUID;

public interface ReservationService {
    ReservationResponse createReservation(UUID roomId, ReservationRequest request);
    ReservationResponse getById(UUID uuid);

    void cancelReservation(UUID roomId);
}