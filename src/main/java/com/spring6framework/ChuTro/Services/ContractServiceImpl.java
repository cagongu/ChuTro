package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.dto.request.ContractRequest;
import com.spring6framework.ChuTro.dto.request.RenewContractRequest;
import com.spring6framework.ChuTro.dto.response.ContractResponse;
import com.spring6framework.ChuTro.entities.Contract;
import com.spring6framework.ChuTro.entities.Reservation;
import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.enums.ActiveStatus;
import com.spring6framework.ChuTro.enums.RoomStatus;
import com.spring6framework.ChuTro.mappers.ContractMapper;
import com.spring6framework.ChuTro.repositories.ContractRepository;
import com.spring6framework.ChuTro.repositories.ReservationRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
import com.spring6framework.ChuTro.repositories.ServiceCustomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractServiceImpl implements ContractService {
    private final RoomRepository roomRepository;
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;
    private final ReservationRepository reservationRepository;
    private final ServiceCustomRepository serviceCustomRepository;

    @Override
    public ContractResponse getContractById(UUID contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        return contractMapper.contactToContractResponse(contract);
    }

    @Override
    public ContractResponse renewContract(UUID contractId, RenewContractRequest request) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        contract.getRoom().setRentalPrice(request.getRentPrice());
        contract.setRenewalDate(request.getRenewalDate());
        contract.setRenewalEndDate(request.getRenewalEndDate());
        contract.setTerminationDate(request.getRenewalEndDate());

        contractRepository.save(contract);

        return contractMapper.contactToContractResponse(contract);
    }

    @Override
    public ContractResponse saveNewContact(ContractRequest request) {
        Contract contract = contractMapper.contactRequestToContract(request);

        Room room = roomRepository.findById(request.getRoom().getRoomId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        room.setMoveInDate(request.getMoveInDate());

        if (room.getServiceCustoms().size() > 0) {
            room.getServiceCustoms().clear();
            room.getServiceCustoms().addAll(request.getRoom().getServiceCustoms());
        }

        Reservation reservation = Reservation.builder()
                .contactNumber(request.getPhoneNumber())
                .reservationDate(new Timestamp(System.currentTimeMillis()))
                .moveInDate(request.getMoveInDate())
                .depositAmount(request.getRoom().getDepositAmount())
                .status(ActiveStatus.ACTIVE_STATUS)
                .tenantName(request.getTenantName())
                .build();

        if (room.getReservation().isEmpty() ||
                room.getReservation().stream()
                        .noneMatch(Reservation::isCurrent)) {
            reservationRepository.save(reservation);
            room.getReservation().add(reservation);
        }

        room.setMoveInDate(request.getMoveInDate());
        room.setStatus(RoomStatus.OCCUPIED);
        roomRepository.save(room);

        contract.setRoom(room);
        contractRepository.save(contract);

        room.getContracts().add(contract);
        roomRepository.save(room);

        return contractMapper.contactToContractResponse(contract);
    }

    @Override
    public void cancelContract(UUID contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        contract.setRoom(null);

        contractRepository.delete(contract);
    }
}
