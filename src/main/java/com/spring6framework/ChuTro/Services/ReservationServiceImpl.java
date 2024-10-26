package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.dto.request.ReservationRequest;
import com.spring6framework.ChuTro.dto.response.ReservationResponse;
import com.spring6framework.ChuTro.entities.Reservation;
import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.mappers.ReservationMapper;
import com.spring6framework.ChuTro.repositories.ReservationRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public ReservationResponse createReservation(UUID roomId, ReservationRequest request) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        if (room.getReservation() != null) {
            throw new AppException(ErrorCode.ALREADY_RESERVED);
        }

        Reservation reservation = reservationMapper.reservationRequestToReservation(request);
        reservation.setReservationId(UUID.randomUUID());

        reservationRepository.save(reservation);
        room.setReservation(reservation);
        roomRepository.save(room);

        return reservationMapper.reservationToReservationResponse(reservation);
    }

    @Override
    public ReservationResponse getById(UUID uuid) {
        return reservationMapper.reservationToReservationResponse(
                reservationRepository.findById(uuid)
                        .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND)));
    }

    //    goi khi ket thuc thue hoac huy coc
    @Override
    public void cancelReservation(UUID roomId) {
        Room saveRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        if(saveRoom.getReservation() == null){
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        saveRoom.setReservation(null);

        roomRepository.save(saveRoom);
    }
}
