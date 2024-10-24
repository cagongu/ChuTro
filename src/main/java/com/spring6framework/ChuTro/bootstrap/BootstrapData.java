package com.spring6framework.ChuTro.bootstrap;

import com.spring6framework.ChuTro.repositories.ServiceRepository;
import com.spring6framework.ChuTro.entities.HousesForRent;
import com.spring6framework.ChuTro.entities.Room;
import com.spring6framework.ChuTro.entities.Service;
import com.spring6framework.ChuTro.enums.*;
import com.spring6framework.ChuTro.repositories.HousesForRentRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final RoomRepository roomRepository;
    private final HousesForRentRepository housesForRentRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public void run(String... args) throws Exception {
        loadHouseForRentData();
        loadRoomData();
    }

    private void addingDefaultService(Room room) {
        if (room.getServices().isEmpty()) {
            Service electric = Service.builder()
                    .serviceId(UUID.randomUUID())
                    .serviceName("Tiền điện")
                    .serviceMetrics(0)
                    .costType(CostType.FIXED)
                    .servicePriceDefault(1700.0)
                    .build();

            Service water = Service.builder()
                    .serviceId(UUID.randomUUID())
                    .serviceName("Tiền nước")
                    .serviceMetrics(0)
                    .costType(CostType.FIXED)
                    .servicePriceDefault(18000.0)
                    .build();

            Service trash = Service.builder()
                    .serviceId(UUID.randomUUID())
                    .serviceName("Tiền rác")
                    .serviceMetrics(0)
                    .costType(CostType.FIXED)
                    .servicePriceDefault(15000.0)
                    .build();

            Service wifi = Service.builder()
                    .serviceId(UUID.randomUUID())
                    .serviceName("Tiền wifi")
                    .serviceMetrics(0)
                    .costType(CostType.FIXED)
                    .servicePriceDefault(50000.0)
                    .build();

            serviceRepository.save(electric);
            serviceRepository.save(wifi);
            serviceRepository.save(trash);
            serviceRepository.save(water);

            room.getServices().add(electric);
            room.getServices().add(water);
            room.getServices().add(wifi);
            room.getServices().add(trash);
        }
    }


    private void loadHouseForRentData() {
        if (housesForRentRepository.count() == 0) {
            HousesForRent housesRorRent = HousesForRent.builder()
                    .id(UUID.randomUUID())
                    .name("LUCIA")
                    .typeOfRental(TypeOfRental.Dormitory)
                    .totalRooms(10)
                    .totalFloors(2)
                    .activeStatus(ActiveStatus.ACTIVE_STATUS)
                    .address(null)
                    .build();

            housesForRentRepository.save(housesRorRent);
        }
    }

    private void loadRoomData() {
        if (roomRepository.count() == 0) {
            Room room1 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(1)
                    .roomName("Phòng 1")
                    .floorNumber(1)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .depositAmount(300000.0)
                    .invoiceDate(6)
                    .billingCycle(1)
                    .financialStatus(FinancialStatus.BILLING_CYCLE)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .housesForRent(housesForRentRepository.findAll().getFirst())
                    .build();

            Room room2 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(2)
                    .roomName("Phòng 2")
                    .floorNumber(1)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .housesForRent(housesForRentRepository.findAll().getFirst())
                    .build();

            Room room3 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(3)
                    .roomName("Phòng 3")
                    .floorNumber(1)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .housesForRent(housesForRentRepository.findAll().getFirst())
                    .build();

            Room room4 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(4)
                    .roomName("Phòng 4")
                    .floorNumber(1)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .housesForRent(housesForRentRepository.findAll().getFirst())
                    .build();

            Room room5 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(5)
                    .roomName("Phòng 5")
                    .floorNumber(1)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .housesForRent(housesForRentRepository.findAll().getFirst())
                    .build();

            Room room6 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(6)
                    .roomName("Phòng 6")
                    .floorNumber(2)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .housesForRent(housesForRentRepository.findAll().getFirst())
                    .build();

            Room room7 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(7)
                    .roomName("Phòng 7")
                    .floorNumber(2)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .housesForRent(housesForRentRepository.findAll().getFirst())
                    .build();

            Room room8 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(8)
                    .roomName("Phòng 8")
                    .floorNumber(2)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .housesForRent(housesForRentRepository.findAll().getFirst())
                    .build();

            Room room9 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(9)
                    .roomName("Phòng 9")
                    .floorNumber(3)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .housesForRent(housesForRentRepository.findAll().getFirst())
                    .build();

            Room room10 = Room.builder()
                    .roomId(UUID.randomUUID())
                    .roomNumber(10)
                    .roomName("Phòng 10")
                    .floorNumber(2)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .housesForRent(housesForRentRepository.findAll().getFirst())
                    .build();


            List<Room> rooms = new ArrayList<>();

            rooms.add(room1);
            rooms.add(room2);
            rooms.add(room3);
            rooms.add(room4);
            rooms.add(room5);
            rooms.add(room6);
            rooms.add(room7);
            rooms.add(room8);
            rooms.add(room9);
            rooms.add(room10);

            for (Room room : rooms) {
                roomRepository.save(room);
                addingDefaultService(room);
            }

        }
    }
}
