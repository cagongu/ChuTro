package com.spring6framework.ChuTro.Services;

import com.spring6framework.ChuTro.controller.NotFoundException;
import com.spring6framework.ChuTro.entities.Building;
import com.spring6framework.ChuTro.entities.Dormitory;
import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.entities.RoomType;

import com.spring6framework.ChuTro.mappers.RoomMapper;
import com.spring6framework.ChuTro.model.RoomDTO;

import com.spring6framework.ChuTro.repositories.BuildingRepository;
import com.spring6framework.ChuTro.repositories.DormitoryRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
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
    private final BuildingRepository buildingRepository;
    private final DormitoryRepository dormitoryRepository;

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @Override
    public Optional<RoomDTO> getRoomById(UUID id) {
        log.info("Get Beer by Id - in service");
        return Optional.ofNullable(roomMapper.roomToRoomDto(roomRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public Page<RoomDTO> getAll(String roomName, RoomType roomType, Integer pageNumber, Integer pageSize) {
        log.info("List Beers - in service");

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<Room> roomPage;

        if (StringUtils.hasText(roomName) && roomType == null) {
            roomPage = getAllByName(roomName, pageRequest);
        } else if (!StringUtils.hasText(roomName) && roomType != null) {
            roomPage = getAllByRoomType(roomType, pageRequest);
        } else if (StringUtils.hasText(roomName) && roomType != null) {
            roomPage = GetAllByNameAndType(roomName, roomType, pageRequest);
        } else {
            roomPage = roomRepository.findAll(pageRequest);
        }

        return roomPage.map(roomMapper::roomToRoomDto);
    }

    private Page<Room> GetAllByNameAndType(String roomName, RoomType roomType, Pageable pageRequest) {
        return roomRepository.findAllByRoomNameIsLikeIgnoreCaseAndRoomType(roomName, roomType, pageRequest);
    }

    private Page<Room> getAllByRoomType(RoomType roomType, Pageable pageRequest) {
        return roomRepository.findAllByRoomType(roomType, pageRequest);
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
    public RoomDTO saveRoom(RoomDTO roomDTO) {
        Room saveRoom = roomMapper.roomDtoToRoom(roomDTO);
        if(roomDTO.getBuildingId() != null){
            Building building = buildingRepository.findById(roomDTO.getBuildingId()).orElseThrow(NotFoundException::new);
            roomRepository.save(saveRoom);
            building.getRooms().add(saveRoom);
        }else{
            Dormitory dormitory = dormitoryRepository.findById(roomDTO.getDormitoryId()).orElseThrow(NotFoundException::new);
            roomRepository.save(saveRoom);
            dormitory.getRooms().add(saveRoom);
        }

        return roomMapper.roomToRoomDto(saveRoom);
    }

    @Override
    public Optional<RoomDTO> updateRoomById(UUID id, RoomDTO roomDTO) {
        RoomDTO foundRoom = getRoomById(id).orElseThrow(NotFoundException::new);

        foundRoom.setRoomNumber(roomDTO.getRoomNumber());
        foundRoom.setRoomName(roomDTO.getRoomName());
        foundRoom.setFloorNumber(roomDTO.getFloorNumber());
        foundRoom.setArea(roomDTO.getArea());
        foundRoom.setPrice(roomDTO.getPrice());
        foundRoom.setRoomType(roomDTO.getRoomType());
        foundRoom.setElectricityDefault(roomDTO.getElectricityDefault());
        foundRoom.setWaterDefault(roomDTO.getWaterDefault());
        foundRoom.setMaxOccupants(roomDTO.getMaxOccupants());
        foundRoom.setStatus(roomDTO.getStatus());
        foundRoom.setDormitoryId(foundRoom.getDormitoryId());
        foundRoom.setBuildingId(foundRoom.getBuildingId());

        Room updatedRoom = roomRepository.save(
                roomMapper.roomDtoToRoom(foundRoom));
        return Optional.of(roomMapper.roomToRoomDto(updatedRoom));
    }

    @Override
    public void deleteRoomById(UUID id) {
        Room room = roomRepository.findById(id).orElseThrow(NotFoundException::new);

        if(room.getBuilding() != null){
            Building building = buildingRepository.findById(room.getBuilding().getBuildingId()).orElse(null);
            assert building != null;
            building.getRooms().remove(room);
            roomRepository.delete(room);
        }
        else {
            Dormitory dormitory = dormitoryRepository.findById(room.getDormitory().getDormitoryId()).orElse(null);
            assert dormitory != null;
            dormitory.getRooms().remove(room);
            roomRepository.delete(room);
        }
    }

    @Override
    public Optional<RoomDTO> patchRoomId(UUID id, RoomDTO roomDTO) {
        RoomDTO foundRoom = getRoomById(id).orElseThrow(NotFoundException::new);

        if (roomDTO.getRoomNumber() != null) {
            foundRoom.setRoomNumber(roomDTO.getRoomNumber());
        }
        if (StringUtils.hasText(foundRoom.getRoomName())) {
            foundRoom.setRoomName(roomDTO.getRoomName());
        }
        if (roomDTO.getFloorNumber() != null) {
            foundRoom.setFloorNumber(roomDTO.getFloorNumber());
        }
        if (roomDTO.getArea() != null) {
            foundRoom.setArea(roomDTO.getArea());
        }
        if (roomDTO.getPrice() != null) {
            foundRoom.setPrice(roomDTO.getPrice());
        }
        if (StringUtils.hasText(String.valueOf(roomDTO.getRoomType()))) {
            foundRoom.setRoomType(roomDTO.getRoomType());
        }
        if (roomDTO.getElectricityDefault() != foundRoom.getElectricityDefault()) {
            foundRoom.setElectricityDefault(roomDTO.getElectricityDefault());
        }
        if (roomDTO.getWaterDefault() != foundRoom.getWaterDefault()) {
            foundRoom.setWaterDefault(roomDTO.getWaterDefault());
        }
        if (roomDTO.getMaxOccupants() != foundRoom.getMaxOccupants()) {
            foundRoom.setMaxOccupants(roomDTO.getMaxOccupants());
        }
        if (roomDTO.getStatus() != foundRoom.getStatus()) {
            foundRoom.setStatus(roomDTO.getStatus());
        }
        if (roomDTO.getRoomId() != foundRoom.getDormitoryId()) {
            foundRoom.setDormitoryId(foundRoom.getDormitoryId());
        }
        if (roomDTO.getBuildingId() != foundRoom.getBuildingId()) {
            foundRoom.setBuildingId(foundRoom.getBuildingId());
        }

        Room updatedRoom = roomRepository.save(
                roomMapper.roomDtoToRoom(foundRoom));
        return Optional.of(roomMapper.roomToRoomDto(updatedRoom));
    }
}
