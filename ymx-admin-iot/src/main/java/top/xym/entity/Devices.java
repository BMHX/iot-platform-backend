package top.xym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备表
 *
 * @author YourName
 * @since YourDate
 */
@Data
@TableName("devices")
public class Devices implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @TableField("device_name")
    private String deviceName;

    @TableField("device_code")
    private String deviceCode;

    private String status;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableField("device_type")
    private String deviceType;


    @TableField("admin_id")
    private Integer adminId;

    @TableField("device_integration")
    private Integer deviceIntegration;
    private String latitude;

    private String longitude;

}
