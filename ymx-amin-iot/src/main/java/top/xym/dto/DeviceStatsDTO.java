package top.xym.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeviceStatsDTO {
    private Integer statusId;
    private Integer deviceId;
    private String statusData;
    private LocalDateTime createdAt;
} 