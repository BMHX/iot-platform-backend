package top.xym.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeviceDTO {
    private Integer id;
    private String deviceName;
    private String deviceCode;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String deviceType;
    private Integer adminId;
    private String latitude;
    private String longitude;
    private Integer deviceIntegration;
}
