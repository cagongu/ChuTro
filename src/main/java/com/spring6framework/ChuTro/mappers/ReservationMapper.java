package com.spring6framework.ChuTro.mappers;

import com.spring6framework.ChuTro.dto.request.ReservationRequest;
import com.spring6framework.ChuTro.dto.response.ReservationResponse;
import com.spring6framework.ChuTro.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReservationMapper {
    @Mapping(target = "reservationId", ignore = true)
    Reservation reservationRequestToReservation(ReservationRequest request);
    ReservationResponse reservationToReservationResponse(Reservation reservation);

    Reservation reservationResponseToReservation(ReservationResponse response);
}
