package com.spring6framework.ChuTro.bootstrap;

import com.spring6framework.ChuTro.entities.*;
import com.spring6framework.ChuTro.repositories.BuildingRepository;
import com.spring6framework.ChuTro.repositories.DormitoryRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final RoomRepository roomRepository;
    private final DormitoryRepository dormitoryRepository;
    private final BuildingRepository buildingRepository;

    @Override
    public void run(String... args) throws Exception {
        loadDormitoryData();
        loadBuildingData();
        loadRoomData();
    }

    private void loadBuildingData() {
        if(buildingRepository.count() == 0){
            Building building1 = Building.builder()
                    .buildingId(UUID.randomUUID())
                    .buildingName("Tòa nhà A")
                    .numberOfFloors(5)
                    .address(null)
                    .build();

            Building building2 = Building.builder()
                    .buildingId(UUID.randomUUID())
                    .buildingName("Tòa nhà B")
                    .numberOfFloors(10)
                    .address(null)
                    .build();

            buildingRepository.save(building1);
            buildingRepository.save(building2);
        }
    }


    private void loadDormitoryData() {
        if(dormitoryRepository.count() == 0){
            Dormitory dormitory1 = Dormitory.builder()
                    .dormitoryId(UUID.randomUUID())
                    .DormitoryName("Ký túc xá 1")
                    .address(null)
                    .build();

            dormitoryRepository.save(dormitory1);
        }
    }

    private void loadRoomData() {
        if(roomRepository.count() == 0){
            Room room1 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(1L)
                    .roomName("Phòng 1")
                    .floorNumber(1L)
                    .area(20.0)
                    .price(300000.0)
                    .roomType(RoomType.Dormitory)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.empty)
                    .building(null)
                    .dormitory(dormitoryRepository.findAll().getFirst())
                    .build();

            Room room2 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(102L)
                    .roomName("Phòng 102")
                    .floorNumber(1L)
                    .area(50.0)
                    .price(5500000.0)
                    .roomType(RoomType.Building)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.empty)
                    .building(buildingRepository.findAll().getFirst())
                    .dormitory(null)
                    .build();

            Room room3 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(202L)
                    .roomName("Phòng 202")
                    .floorNumber(2L)
                    .area(50.0)
                    .price(5500000.0)
                    .roomType(RoomType.Building)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.empty)
                    .building(buildingRepository.findAll().get(1))
                    .dormitory(null)
                    .build();

            roomRepository.save(room1);
            roomRepository.save(room2);
            roomRepository.save(room3);
        }
    }
}
