package top.xym.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("device_protocol")
public class DeviceProtocolEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer deviceId;

    private Integer protocolId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime bindTime;
}
