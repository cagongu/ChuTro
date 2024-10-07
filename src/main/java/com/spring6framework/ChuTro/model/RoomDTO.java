package com.spring6framework.ChuTro.model;

import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.entities.RoomStatus;
import com.spring6framework.ChuTro.entities.RoomType;
import org.mapstruct.Mapper;

import java.util.UUID;

public class RoomDTO {
    private UUID roomId;
    private Long roomNumber;
    private String roomName;
    private Long floorNumber;
    private Double area;
    private Double price;
    private RoomStatus status;
    private double electricityDefault;
    private double waterDefault;
    private RoomType roomType;
    private int maxOccupants;
}
