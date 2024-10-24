package com.spring6framework.ChuTro.controller;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.Services.RoomService;
import com.spring6framework.ChuTro.dto.request.RoomCreationRequest;
import com.spring6framework.ChuTro.dto.request.RoomUpdateRequest;
import com.spring6framework.ChuTro.dto.response.ApiResponse;
import com.spring6framework.ChuTro.dto.response.RoomResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class RoomController {
    public static final String ROOM_PATH = "/room";
    public static final String ROOM_PATH_ID = ROOM_PATH + "/{roomId}";

    private final RoomService roomService;

    @GetMapping(ROOM_PATH)
    public ApiResponse<Page<RoomResponse>> getAll(@RequestParam(required = false) String roomName,
                                                  @RequestParam(required = false) Integer pageNumber,
                                                  @RequestParam(required = false) Integer pageSize) {
        Page<RoomResponse> rooms = roomService.getAll(roomName, pageNumber, pageSize);

        return ApiResponse.<Page<RoomResponse>>builder()
                .result(rooms)
                .build();
    }

    @GetMapping(ROOM_PATH_ID)
    public ApiResponse<RoomResponse> getRoomById(@PathVariable("roomId") UUID id) {
        return ApiResponse.<RoomResponse>builder()
                .result(roomService.getRoomById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND)))
                .build();
    }

    @PostMapping(ROOM_PATH)
    public ApiResponse<ResponseEntity<RoomResponse>> saveNewRoom(@RequestBody RoomCreationRequest request) {
        RoomResponse saveRoom = roomService.saveRoom(request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", ROOM_PATH + "/" + saveRoom.getRoomId().toString());

        return ApiResponse.<ResponseEntity<RoomResponse>>builder()
                .result(new ResponseEntity<>(headers, HttpStatus.CREATED))
                .build();


    }

    @PutMapping(ROOM_PATH_ID)
    public ApiResponse<ResponseEntity<RoomResponse>> updateRoomById(@PathVariable("roomId") UUID uuid, @RequestBody RoomUpdateRequest request) {
        if (roomService.updateRoomById(uuid, request).isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        return ApiResponse.<ResponseEntity<RoomResponse>>builder()
                .result(new ResponseEntity<RoomResponse>(HttpStatus.OK))
                .build();
    }

    @DeleteMapping(ROOM_PATH_ID)
    public ApiResponse<ResponseEntity<RoomResponse>> deleteRoomById(@PathVariable("roomId") UUID uuid) {

        roomService.deleteRoomById(uuid);

        return ApiResponse.<ResponseEntity<RoomResponse>>builder()
                .result(new ResponseEntity<RoomResponse>(HttpStatus.NO_CONTENT))
                .build();
    }
}
