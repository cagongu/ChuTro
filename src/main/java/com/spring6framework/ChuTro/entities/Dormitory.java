package com.spring6framework.ChuTro.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "dormitory")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dormitory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID dormitoryId;
    private String DormitoryName;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
}
