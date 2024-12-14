package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.dto.request.ReservationRequest;
import com.spring6framework.ChuTro.dto.response.ReservationResponse;
import com.spring6framework.ChuTro.entities.Reservation;
import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.enums.ActiveStatus;
import com.spring6framework.ChuTro.enums.RoomStatus;
import com.spring6framework.ChuTro.mappers.ReservationMapper;
import com.spring6framework.ChuTro.repositories.ReservationRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
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

        if (!room.getReservation().isEmpty()) {
            throw new AppException(ErrorCode.ALREADY_RESERVED);
        }

        room.setMoveInDate(request.getMoveInDate());
        room.setStatus(RoomStatus.RESERVED);

        Reservation reservation = reservationMapper.reservationRequestToReservation(request);
        reservation.setCurrent(true);

        reservationRepository.save(reservation);
        room.getReservation().add(reservation);
        roomRepository.save(room);

        return reservationMapper.reservationToReservationResponse(reservation);
    }

    @Override
    public ReservationResponse getById(UUID uuid) {
        return reservationMapper.reservationToReservationResponse(
                reservationRepository.findById(uuid)
                        .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND)));
    }

    @Override
    public ReservationResponse getCurrentReservation(UUID roomId) {
        // Tìm Room dựa vào roomId
        Room foundRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        // Lấy danh sách Reservation và tìm Reservation mới nhất
        Reservation latestReservation = foundRoom.getReservation().stream()
                .max(Comparator.comparing(Reservation::getCreatedDate))
                .orElse(null);

        // Chuyển đổi sang DTO và trả về
        return reservationMapper.reservationToReservationResponse(latestReservation);
    }


    //    goi khi ket thuc thue hoac huy coc
    @Override
    public void cancelReservation(UUID roomId) {
        Room saveRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        // Không cần kiểm tra null nếu đã khai báo @Builder.Default
        Set<Reservation> reservations = saveRoom.getReservation();

        // Nếu không có reservation, ném lỗi
        if (reservations.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        saveRoom.setMoveInDate(null);
        saveRoom.setStatus(RoomStatus.VACANT);

        // Lấy reservation mới nhất
        Reservation latestReservation = reservationMapper.reservationResponseToReservation(getCurrentReservation(roomId));

        if (latestReservation != null) {
            latestReservation.setStatus(ActiveStatus.DELETED_STATUS);  // Cập nhật trạng thái cho reservation
            latestReservation.setCurrent(false);
        }

        // Lưu lại room và thay đổi trạng thái reservation
        roomRepository.save(saveRoom);
    }

}
