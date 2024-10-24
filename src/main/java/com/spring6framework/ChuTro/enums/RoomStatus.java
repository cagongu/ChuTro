package com.spring6framework.ChuTro.enums;

public enum RoomStatus {
    OCCUPIED,                // Đang ở
    VACANT,                  // Đang trống
    NOTICE_GIVEN,            // Đang báo kết thúc
    CONTRACT_EXPIRING_SOON,  // Sắp hết hạn hợp đồng
    CONTRACT_OVERDUE,        // Đã quá hạn hợp đồng
    RESERVED,                // Đang cọc giữ chỗ
    PAYMENT_DUE              // Đang nợ tiền
}
