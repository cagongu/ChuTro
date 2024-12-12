package com.spring6framework.ChuTro.entities;

import com.spring6framework.ChuTro.enums.ActiveStatus;
import com.spring6framework.ChuTro.enums.TypeOfRental;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HousesForRent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    private String name;

    private TypeOfRental typeOfRental;

    private int totalFloors;
    private int totalRooms;

    @Column(length = 36, columnDefinition = "varchar(36)")
    private String ownerId;

    @Builder.Default
    @OneToMany(mappedBy = "housesForRent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Builder.Default
    private ActiveStatus activeStatus = ActiveStatus.ACTIVE_STATUS;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Service> services = new HashSet<>();
}
