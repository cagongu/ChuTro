package com.spring6framework.ChuTro.bootstrap;

import com.spring6framework.ChuTro.Exception.AppException;
import com.spring6framework.ChuTro.Exception.ErrorCode;
import com.spring6framework.ChuTro.entities.*;
import com.spring6framework.ChuTro.enums.*;
import com.spring6framework.ChuTro.repositories.HousesForRentRepository;
import com.spring6framework.ChuTro.repositories.RoomRepository;
import com.spring6framework.ChuTro.repositories.ServiceCustomRepository;
import com.spring6framework.ChuTro.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final RoomRepository roomRepository;
    private final HousesForRentRepository housesForRentRepository;
    private final ServiceCustomRepository serviceCustomRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public void run(String... args) throws Exception {
        loadHouseForRentData();
        loadService();
        loadRoomData();
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

    private void loadService() {
        if (serviceRepository.count() == 0) {
            Service electric = Service.builder()
                    .serviceId(UUID.randomUUID())
                    .serviceName("Tiền điện")
                    .serviceMetrics(0)
                    .costType(CostType.FIXED)
                    .servicePriceDefault(1700.0)
                    .unitOfMeasurement(UnitOfMeasurement.KWH)
                    .build();

            Service water = Service.builder()
                    .serviceId(UUID.randomUUID())
                    .serviceName("Tiền nước")
                    .serviceMetrics(0)
                    .costType(CostType.FIXED)
                    .servicePriceDefault(18000.0)
                    .unitOfMeasurement(UnitOfMeasurement.CUBIC_METER)
                    .build();

            Service trash = Service.builder()
                    .serviceId(UUID.randomUUID())
                    .serviceName("Tiền rác")
                    .serviceMetrics(0)
                    .costType(CostType.FIXED)
                    .servicePriceDefault(15000.0)
                    .unitOfMeasurement(UnitOfMeasurement.MONTH)
                    .build();

            Service wifi = Service.builder()
                    .serviceId(UUID.randomUUID())
                    .serviceName("Tiền wifi")
                    .serviceMetrics(0)
                    .costType(CostType.FIXED)
                    .servicePriceDefault(50000.0)
                    .unitOfMeasurement(UnitOfMeasurement.MONTH)
                    .build();

            HousesForRent housesForRent = housesForRentRepository.findAll().get(0);
            housesForRent.getServices().add(electric);
            housesForRent.getServices().add(water);
            housesForRent.getServices().add(trash);
            housesForRent.getServices().add(wifi);

            housesForRentRepository.save(housesForRent);
        }
    }

    public void loadRoomData() {
        HousesForRent housesForRent = housesForRentRepository.findAll().get(0);
        if (roomRepository.count() == 0) {
            Room room1 = Room.builder()
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
                    .housesForRent(housesForRent)
                    .status(RoomStatus.VACANT)
                    .contracts(null)
                    .build();

            Room room2 = Room.builder()
                    .roomNumber(2)
                    .roomName("Phòng 2")
                    .floorNumber(1)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .housesForRent(housesForRent)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .contracts(null)
                    .build();

            Room room3 = Room.builder()
                    .roomNumber(3)
                    .roomName("Phòng 3")
                    .floorNumber(1)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .housesForRent(housesForRent)
                    .status(RoomStatus.VACANT)
                    .contracts(null)
                    .build();

            Room room4 = Room.builder()
                    .roomNumber(4)
                    .roomName("Phòng 4")
                    .floorNumber(1)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .housesForRent(housesForRent)
                    .status(RoomStatus.VACANT)
                    .contracts(null)
                    .build();

            Room room5 = Room.builder()
                    .roomNumber(5)
                    .roomName("Phòng 5")
                    .floorNumber(1)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .housesForRent(housesForRent)
                    .status(RoomStatus.VACANT)
                    .contracts(null)
                    .build();

            Room room6 = Room.builder()
                    .roomNumber(6)
                    .roomName("Phòng 6")
                    .floorNumber(2)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .housesForRent(housesForRent)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .contracts(null)
                    .build();

            Room room7 = Room.builder()
                    .roomNumber(7)
                    .roomName("Phòng 7")
                    .floorNumber(2)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .housesForRent(housesForRent)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .contracts(null)
                    .build();

            Room room8 = Room.builder()
                    .roomNumber(8)
                    .roomName("Phòng 8")
                    .floorNumber(2)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .housesForRent(housesForRent)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .contracts(null)
                    .build();

            Room room9 = Room.builder()
                    .roomNumber(9)
                    .roomName("Phòng 9")
                    .floorNumber(3)
                    .area(20.0)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .housesForRent(housesForRent)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .contracts(null)
                    .build();

            Room room10 = Room.builder()
                    .roomNumber(10)
                    .roomName("Phòng 10")
                    .floorNumber(2)
                    .area(20.0)
                    .housesForRent(housesForRent)
                    .rentalPrice(300000.0)
                    .electricityDefault(3500.0)
                    .waterDefault(20000.0)
                    .maxOccupants(3)
                    .status(RoomStatus.VACANT)
                    .contracts(null)
                    .build();

            housesForRent.getRooms().add(room1);
            housesForRent.getRooms().add(room2);
            housesForRent.getRooms().add(room3);
            housesForRent.getRooms().add(room4);
            housesForRent.getRooms().add(room5);
            housesForRent.getRooms().add(room6);
            housesForRent.getRooms().add(room7);
            housesForRent.getRooms().add(room8);
            housesForRent.getRooms().add(room9);
            housesForRent.getRooms().add(room10);

            housesForRentRepository.save(housesForRent);


            List<Room> updateRooms = roomRepository.findAll();

            for (Room room : updateRooms) {
                addingFurniture(room.getRoomId());
                addServiceCustom(room.getRoomId());
            }
        }
    }

    public void addServiceCustom(UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        HousesForRent housesForRent = room.getHousesForRent();

        housesForRent.getServices().forEach(service -> {
            Service existingService = serviceRepository.findById(service.getServiceId())
                    .orElse(service); // Nếu không tìm thấy, dùng service hiện tại

            ServiceCustom serviceCustom = ServiceCustom.builder()
                    .service(existingService)
                    .isActive(true)
                    .build();

            room.getServiceCustoms().add(serviceCustom);
        });
        roomRepository.save(room);
    }

    private void addingFurniture(UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        if (room.getFurnitures().isEmpty()) {
            Furniture furniture = Furniture.builder()
                    .name("Tủ lạnh")
                    .assetValue(15000.0)
                    .totalNumber(10)
                    .unitOfMeasurement(UnitOfMeasurement.PIECE)
                    .build();

            room.getFurnitures().add(furniture);
            roomRepository.save(room);
        }
    }


}
