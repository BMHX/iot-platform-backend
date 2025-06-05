package top.xym.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeviceStatsVO {
    private Integer statusId;
    private Integer deviceId;
    private String statusData;
    private LocalDateTime createdAt;
    private String deviceName;  // 设备名称，用于展示
    private String formattedTime;  // 格式化后的时间，用于展示
} 