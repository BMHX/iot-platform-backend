package top.xym.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备表 数据传输对象
 *
 * @author YourName
 * @since YourDate
 */
@Data
public class DeviceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer userId;
    private String deviceName;
    private String deviceCode;
    private String status;
    private String deviceType;
    private Integer roomId;
    private Integer adminId;
    private String latitude;
    private String longitude;
    // createdAt 和 updatedAt 通常由后端自动处理
}
