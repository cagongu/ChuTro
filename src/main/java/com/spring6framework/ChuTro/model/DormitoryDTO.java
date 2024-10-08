package com.spring6framework.ChuTro.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Builder
@Data
public class DormitoryDTO {
    private UUID dormitoryId;
    private String DormitoryName;
}
