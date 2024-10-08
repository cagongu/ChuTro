package com.spring6framework.ChuTro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring6framework.ChuTro.entities.*;
import com.spring6framework.ChuTro.mappers.RoomMapper;
import com.spring6framework.ChuTro.model.RoomDTO;
import com.spring6framework.ChuTro.repositories.BuildingRepository;
import com.spring6framework.ChuTro.repositories.DormitoryRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class RoomControllerTest {
    @Autowired
    RoomController roomController;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    DormitoryRepository dormitoryRepository;

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

    private RoomDTO createNewRoom() {
        return RoomDTO.builder()
                .roomId(UUID.randomUUID())
                .roomNumber(202L)
                .roomName("Room test")
                .floorNumber(2L)
                .area(50.0)
                .price(5500000.0)
                .roomType(RoomType.Building)
                .electricityDefault(3500.0)
                .waterDefault(20000.0)
                .maxOccupants(3)
                .status(RoomStatus.empty)
                .build();
    }

    @Test
    void getAll() throws Exception {
        Page<RoomDTO> dtos = roomController.getAll(null, null, null, null);

        assertThat(dtos.getContent().size()).isGreaterThan(1);
    }

    @Test
    void getById() throws Exception {
        Room room = roomRepository.findAll().getFirst();

        RoomDTO dto = roomController.getRoomById(room.getRoomId());

        assertThat(dto).isNotNull();
    }

    @Test
    void getByIdNotFound() {
        assertThrows(NotFoundException.class, () -> roomController.getRoomById(UUID.randomUUID()));
    }

    @Transactional
    @Test
    void saveNewRoom() throws Exception {
        RoomDTO roomDTO = createNewRoom();
        Dormitory dormitory = dormitoryRepository.findAll().getFirst();
        roomDTO.setDormitoryId(dormitory.getDormitoryId());

        ResponseEntity<RoomDTO> responseEntity = roomController.saveNewRoom(roomDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);
    }

    @Rollback
    @Transactional
    @Test
    void updateRoomById() throws Exception {
        RoomDTO roomDTO = roomMapper.roomToRoomDto(roomRepository.findAll().getFirst());

        roomDTO.setArea(10000.0);

        mockMvc.perform(put(RoomController.ROOM_PATH_ID, roomDTO.getRoomId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomDTO)))
                .andDo(print())
                .andExpect(status().isNoContent());

        Room updatedRoom = roomRepository.findById(roomDTO.getRoomId()).orElse(null);
        assertNotNull(updatedRoom, "Room should not be null");

        assertEquals(10000.0, updatedRoom.getArea(), "Area should be updated to 10000.0");
    }

    @Test
    void updateRoomByIdNotFound() {
        assertThrows(NotFoundException.class, () -> roomController.updateRoomById(UUID.randomUUID(), createNewRoom()));
    }

    @Rollback
    @Transactional
    @Test
    void patchRoomById() throws Exception {
        RoomDTO roomDTO = roomMapper.roomToRoomDto(roomRepository.findAll().get(1));

        roomDTO.setRoomName("patch room");
        roomDTO.setArea(120.0);

        mockMvc.perform(patch(RoomController.ROOM_PATH_ID, roomDTO.getRoomId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomDTO)))
                .andDo(print())
                .andExpect(status().isNoContent());

        Room patchRoom = roomRepository.findById(roomDTO.getRoomId()).orElse(null);
        assertNotNull(patchRoom);
        assertEquals("patch room", patchRoom.getRoomName());
        assertEquals(120.0, patchRoom.getArea());
    }

    @Rollback
    @Transactional
    @Test
    public void deleteRoom() {
        Room room = roomRepository.findAll().getFirst();
        RoomDTO roomDTO = roomMapper.roomToRoomDto(room);

        ResponseEntity<RoomDTO> responseEntity = roomController.deleteRoomById(roomDTO.getRoomId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
    }
}