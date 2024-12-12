package com.spring6framework.ChuTro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.dto.request.RoomCreationRequest;
import com.spring6framework.ChuTro.dto.request.RoomUpdateRequest;
import com.spring6framework.ChuTro.dto.response.ApiResponse;
import com.spring6framework.ChuTro.dto.response.RoomResponse;
import com.spring6framework.ChuTro.entities.Furniture;
import com.spring6framework.ChuTro.entities.HousesForRent;
import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.enums.RoomStatus;
import com.spring6framework.ChuTro.mappers.RoomMapper;
import com.spring6framework.ChuTro.repositories.HousesForRentRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
class RoomControllerTest {
    @Autowired
    RoomController roomController;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    HousesForRentRepository housesForRentRepository;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    private Furniture createFurniture(){
        return Furniture.builder()
                .name("asd").build();
    }

    private RoomCreationRequest createNewRoom() {
        return RoomCreationRequest.builder()
                .roomNumber(202L)
                .roomName("Room test")
                .floorNumber(2L)
                .area(50.0)
                .rentalPrice(5500000.0)
                .maxOccupants(3)
                .status(RoomStatus.VACANT)
                .furnitures(new HashSet<>(Set.of(createFurniture())))
                .build();
    }

    @Test
    void getAll() throws Exception {
        ApiResponse<Page<RoomResponse>> response = roomController.getAll(null, null, null, null);
        assertThat(response.getResult().getContent().size()).isGreaterThan(1);
    }

    @Test
    void getById() {
        Room room = roomRepository.findAll().get(0);
        ApiResponse<RoomResponse> response = roomController.getRoomById(room.getRoomId());
        assertThat(response.getResult()).isNotNull();
    }

    @Test
    void getByIdNotFound() {
        assertThrows(AppException.class, () -> {
            roomController.getRoomById(UUID.randomUUID());
        });
    }

    @Transactional
    @Test
    void saveNewRoom() throws Exception {
        RoomCreationRequest request = createNewRoom();

        List<HousesForRent> dormitories = housesForRentRepository.findAll();
        if (dormitories.isEmpty()) {
            throw new IllegalStateException("No dormitories found in the database.");
        }

        HousesForRent housesForRent = dormitories.get(0);
        request.setHousesForRentId(housesForRent.getId());

        ApiResponse<ResponseEntity<RoomResponse>> response = roomController.saveNewRoom(request);

        assertThat(response.getResult().getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getResult().getHeaders().getLocation()).isNotNull();

        String[] locationUUID = response.getResult().getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[2]);

        log.info("Saved Room UUID: {}", savedUUID.toString());
    }

    @Rollback
    @Transactional
    @Test
    void updateRoomById() throws Exception {
        Room room = roomRepository.findAll().get(0);

        RoomUpdateRequest request = RoomUpdateRequest.builder()
                .area(10001.0)
                .build();

        mockMvc.perform(put(RoomController.ROOM_PATH_ID, room.getRoomId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());

        Room updatedRoom = roomRepository.findById(room.getRoomId()).orElse(null);
        assertNotNull(updatedRoom, "Room should not be null");
        assertEquals(10001.0, updatedRoom.getArea(), "Area should be updated to 10001.0");
    }

    @Test
    void updateRoomByIdNotFound() {
        RoomUpdateRequest request = RoomUpdateRequest.builder()
                .area(10000.0)
                .build();
        assertThrows(AppException.class, () -> roomController.updateRoomById(UUID.randomUUID(), request));
    }

    @Rollback
    @Transactional
    @Test
    void deleteRoom() {
        Room room = roomRepository.findAll().get(0);
        ApiResponse<ResponseEntity<RoomResponse>> response = roomController.deleteRoomById(room.getRoomId());
        assertThat(response.getResult().getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}