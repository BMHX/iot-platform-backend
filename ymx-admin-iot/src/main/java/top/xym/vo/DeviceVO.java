package top.xym.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备表 值对象
 *
 * @author YourName
 * @since YourDate
 */
@Data
public class DeviceVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String deviceName;
    private String deviceCode;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String deviceType;
    private Integer adminId;
    private Integer deviceIntegration;
    private String latitude;
    private String longitude;

}