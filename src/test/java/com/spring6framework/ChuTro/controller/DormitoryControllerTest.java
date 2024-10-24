package com.spring6framework.ChuTro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring6framework.ChuTro.dto.request.HousesForRentCreationRequest;
import com.spring6framework.ChuTro.dto.request.HousesForRentUpdateRequest;
import com.spring6framework.ChuTro.dto.response.ApiResponse;
import com.spring6framework.ChuTro.dto.response.HousesForRentResponse;
import com.spring6framework.ChuTro.enums.ActiveStatus;
import com.spring6framework.ChuTro.entities.HousesForRent;
import com.spring6framework.ChuTro.mappers.HousesForRentMapper;
import com.spring6framework.ChuTro.repositories.HousesForRentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
class DormitoryControllerTest {
    @Autowired
    DormitoryController dormitoryController;

    @Autowired
    HousesForRentRepository housesForRentRepository;

    @Autowired
    HousesForRentMapper housesForRentMapper;

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

    private HousesForRentCreationRequest createDormitoryRequest() {
        return HousesForRentCreationRequest.builder()
                .name("Test Dormitory")
                .activeStatus(ActiveStatus.ACTIVE_STATUS)
                .build();
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(DormitoryController.DORMITORY_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray());
    }

    @Test
    void getById() throws Exception {
        HousesForRent housesForRent = housesForRentRepository.findAll().get(0);

        mockMvc.perform(get(DormitoryController.DORMITORY_PATH_ID, housesForRent.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(housesForRent.getId().toString()));
    }

    @Test
    void getByIdNotFound() throws Exception {
        mockMvc.perform(get(DormitoryController.DORMITORY_PATH_ID, UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    void createNewDormitory() throws Exception {
        HousesForRentCreationRequest request = createDormitoryRequest();

        ApiResponse<ResponseEntity<HousesForRentResponse>> response = dormitoryController.createNew(request);
        assertThat(response.getResult().getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getResult().getHeaders().getLocation()).isNotNull();
    }

    @Test
    @Rollback
    void updateDormitoryById() throws Exception {
        HousesForRent housesForRent = housesForRentRepository.findAll().get(0);

        HousesForRentUpdateRequest updateRequest = HousesForRentUpdateRequest.builder()
                .name("Updated Dormitory Name")
                .build();

        mockMvc.perform(put(DormitoryController.DORMITORY_PATH_ID, housesForRent.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andDo(print())
                .andExpect(status().isOk());

        HousesForRent updatedHousesRorRent = housesForRentRepository.findById(housesForRent.getId()).orElse(null);
        assertNotNull(updatedHousesRorRent);
        assertEquals("Updated Dormitory Name", updatedHousesRorRent.getName());
    }

    @Rollback
    @Transactional
    @Test
    void deleteRoom() {
        HousesForRent housesForRent = housesForRentRepository.findAll().getFirst();
        ApiResponse<ResponseEntity<Void>> response = dormitoryController.deleteById(housesForRent.getId());
        assertThat(response.getResult().getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        HousesForRent housesForRent1 = housesForRentRepository.findAll().getFirst();
        assert housesForRent1.getActiveStatus().equals(ActiveStatus.DELETED_STATUS);

    }
}