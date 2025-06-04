package top.xym.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@TableName("devices")
public class DeviceEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String deviceName;

    private String deviceCode;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    private String deviceType;

    private Integer adminId;

    private String latitude;

    private String longitude;
    private Integer deviceIntegration;
}