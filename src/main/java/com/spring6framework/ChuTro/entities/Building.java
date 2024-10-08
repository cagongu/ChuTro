package com.spring6framework.ChuTro.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "building")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID buildingId;
    private String buildingName;
    private int numberOfFloors;

    @OneToMany(mappedBy = "building", cascade = CascadeType.PERSIST)
    private Set<Room> rooms;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
}
