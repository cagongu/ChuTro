package com.spring6framework.ChuTro.model;

import com.spring6framework.ChuTro.entities.Building;
import com.spring6framework.ChuTro.entities.Dormitory;
import com.spring6framework.ChuTro.entities.RoomStatus;
import com.spring6framework.ChuTro.entities.RoomType;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;
@Builder
@Data
public class RoomDTO {
    private UUID roomId;

    private Long roomNumber;
    private String roomName;
    private Long floorNumber;
    private Double area;
    private Double price;
    private RoomType roomType;

    private Timestamp createdDate;
    private Timestamp lastModifiedDate;

    private RoomStatus status;
    private double electricityDefault;
    private double waterDefault;
    private int maxOccupants;

    private UUID buildingId;
    private UUID dormitoryId;
}
