package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.dto.request.RoomCreationRequest;
import com.spring6framework.ChuTro.dto.request.RoomUpdateRequest;
import com.spring6framework.ChuTro.dto.response.RoomResponse;
import com.spring6framework.ChuTro.entities.HousesForRent;
import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.enums.RoomStatus;
import com.spring6framework.ChuTro.mappers.RoomMapper;
import com.spring6framework.ChuTro.repositories.HousesForRentRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    private final HousesForRentRepository housesForRentRepository;

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
    public Page<RoomResponse> getAll(String roomName, Integer pageNumber, Integer pageSize) {
        log.info("List Beers - in service");

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<Room> roomPage;

        if (StringUtils.hasText(roomName)) {
            roomPage = getAllByName(roomName, pageRequest);
        } else {
            roomPage = roomRepository.findAll(pageRequest);
        }

        return roomPage.map(roomMapper::roomToRoomResponse);
    }

    @Override
    public Page<RoomResponse> getAllByRoomStatus(RoomStatus roomStatus) {
        return null;
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
        Room saveRoom = roomMapper.roomCreationToRoom(request);
        saveRoom.setRoomId(UUID.randomUUID());

        HousesForRent housesForRent = housesForRentRepository.findById(request.getHousesForRentId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        saveRoom.setHousesForRent(housesForRent);

        return roomMapper.roomToRoomResponse(saveRoom);

    }

    @Override
    public Optional<RoomResponse> updateRoomById(UUID id, RoomUpdateRequest request) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        roomMapper.updateRoom(room, request);

        roomRepository.save(room);

        return Optional.of(roomMapper.roomToRoomResponse(room));
    }

    @Override
    public void deleteRoomById(UUID id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        HousesForRent housesForRent = housesForRentRepository.findById(room.getHousesForRent().getId()).orElse(null);
        assert housesForRent != null;
        housesForRent.getRooms().remove(room);
        roomRepository.delete(room);
    }


}
