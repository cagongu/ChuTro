package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.dto.request.RoomCreationRequest;
import com.spring6framework.ChuTro.dto.request.RoomUpdateRequest;
import com.spring6framework.ChuTro.dto.response.RoomResponse;
import com.spring6framework.ChuTro.entities.Furniture;
import com.spring6framework.ChuTro.entities.HousesForRent;
import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.entities.ServiceCustom;
import com.spring6framework.ChuTro.enums.RoomStatus;
import com.spring6framework.ChuTro.mappers.FurnitureMapper;
import com.spring6framework.ChuTro.mappers.RoomMapper;
import com.spring6framework.ChuTro.mappers.ServiceMapper;
import com.spring6framework.ChuTro.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    private final HousesForRentRepository housesForRentRepository;
    private final ServiceCustomRepository serviceCustomRepository;
    private final FurnitureRepository furnitureRepository;
    private final ServiceMapper serviceMapper;
    private final FurnitureMapper furnitureMapper;
    private final ReservationRepository reservationRepository;

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @Transactional
    @Override
    public Optional<RoomResponse> getRoomById(UUID id) {
        log.info("Get Beer by Id - in service");
        return Optional.ofNullable(roomMapper.roomToRoomResponse(roomRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND))));
    }

    @Transactional
    @Override
    public Page<RoomResponse> getAll(String roomName, RoomStatus roomStatus, Integer pageNumber, Integer pageSize) {
        log.info("List Beers - in service");

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<Room> roomPage;

        if (StringUtils.hasText(roomName)) {
            roomPage = getAllByName(roomName, pageRequest);
        } else if (roomStatus != null) {
            roomPage = getAllByRoomStatus(roomStatus, pageRequest);
        } else {
            roomPage = roomRepository.findAll(pageRequest);
        }

        return roomPage.map(roomMapper::roomToRoomResponse);
    }

    private Page<Room> getAllByRoomStatus(RoomStatus roomStatus, PageRequest pageRequest) {
        Page<Room> roomPage = roomRepository.findAll(pageRequest);

        List<Room> filteredRooms = roomPage
                .stream()
                .filter(room -> room.getStatus() == roomStatus)
                .collect(Collectors.toList());


        return new PageImpl<>(filteredRooms, pageRequest, filteredRooms.size());
    }


    private Page<Room> getAllByName(String roomName, Pageable pageRequest) {
        return roomRepository.findAllByRoomNameIsLikeIgnoreCase("%" + roomName + "%", pageRequest);
    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > 1000) {
                queryPageSize = 1000;
            } else {
                queryPageSize = pageSize;
            }
        }

        Sort sort = Sort.by(Sort.Order.asc("roomName"));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }

    @Override
    public RoomResponse saveRoom(RoomCreationRequest request) {
        if (request.getHousesForRentId() == null) {
            throw new RuntimeException("Create new room must have HousesForRentId");
        }

        Room saveRoom = roomMapper.roomCreationToRoom(request);

        HousesForRent housesForRent = housesForRentRepository.findById(request.getHousesForRentId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        saveRoom.setHousesForRent(housesForRent);
        housesForRent.getRooms().add(saveRoom);

        housesForRent.getServices().forEach(service -> {
            ServiceCustom serviceCustom = ServiceCustom.builder()
                    .service(service)
                    .isActive(true)
                    .build();

            saveRoom.getServiceCustoms().add(serviceCustom);
        });

        housesForRentRepository.save(housesForRent);


        return roomMapper.roomToRoomResponse(saveRoom);
    }

    private void addFurniture(Set<Furniture> request, UUID roomId) {
        Room saveRoom = roomRepository.findById(roomId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        if (request != null) {
            request.forEach(furniture ->
                saveRoom.getFurnitures().add(furniture)
            );
        }
    }

    @Override
    public Optional<RoomResponse> updateRoomById(UUID id, RoomUpdateRequest request) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        roomMapper.updateRoom(room, request);

        roomRepository.save(room);

        return Optional.of(roomMapper.roomToRoomResponse(room));
    }

    //  chinh sua sau khi da them cac thuc the
    @Override
    public void deleteRoomById(UUID id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        HousesForRent housesForRent = housesForRentRepository.findById(room.getHousesForRent().getId()).orElse(null);
        assert housesForRent != null;
        housesForRent.getRooms().remove(room);
        roomRepository.delete(room);
    }
}
