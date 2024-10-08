package com.spring6framework.ChuTro.controller;

import com.spring6framework.ChuTro.Services.RoomService;
import com.spring6framework.ChuTro.entities.RoomType;
import com.spring6framework.ChuTro.model.RoomDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class RoomController {
    public static final String ROOM_PATH = "/api/v1/room";
    public static final String ROOM_PATH_ID = ROOM_PATH + "/{roomId}";

    private final RoomService roomService;

    @GetMapping(ROOM_PATH)
    public Page<RoomDTO> getAll(@RequestParam(required = false) String roomName,
                                @RequestParam(required = false) RoomType roomType,
                                @RequestParam(required = false) Integer pageNumber,
                                @RequestParam(required = false) Integer pageSize) {
        return roomService.getAll(roomName, roomType, pageNumber, pageSize);
    }

    @GetMapping(ROOM_PATH_ID)
    public RoomDTO getRoomById(@PathVariable("roomId") UUID id) {
        return roomService.getRoomById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(ROOM_PATH)
    public ResponseEntity<RoomDTO> saveNewRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO saveRoom = roomService.saveRoom(roomDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", ROOM_PATH + "/" + saveRoom.getRoomId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(ROOM_PATH_ID)
    public ResponseEntity<RoomDTO> updateRoomById(@PathVariable("roomId") UUID uuid,@RequestBody RoomDTO roomDTO){
        if(roomService.updateRoomById(uuid, roomDTO).isEmpty()){
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(ROOM_PATH_ID)
    public ResponseEntity<RoomDTO> patchRoomById(@PathVariable("roomId") UUID uuid, @RequestBody RoomDTO roomDTO){
        roomService.patchRoomId(uuid, roomDTO);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(ROOM_PATH_ID)
    public ResponseEntity<RoomDTO> deleteRoomById(@PathVariable("roomId") UUID uuid){

        roomService.deleteRoomById(uuid);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
