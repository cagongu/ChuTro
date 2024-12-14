package com.spring6framework.ChuTro.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID invoiceId; // Khóa chính

    @OneToOne
    private Room room;
    private String month; // Tháng lập phiếu

    private LocalDate invoiceDate; // Ngày lập hóa đơn
    private LocalDate paymentDeadline; // Hạn đóng tiền

    private boolean isPaid; // Trạng thái thanh toán
    private LocalDate paymentDate; // Ngày thanh toán (nếu có)
    private String reason; // Lý do thu tiền

    private double totalAmount; // Tổng số tiền

    private double cost; // giam gia
    private double finalAmount; // Thành tiền
}