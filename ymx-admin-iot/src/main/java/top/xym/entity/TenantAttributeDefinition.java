package top.xym.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@TableName("tenant_attribute_definitions")
@Schema(description = "租户扩展属性定义表")
public class TenantAttributeDefinition {

    @Schema(description = "属性定义ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "租户类型ID", required = true)
    private Integer typeId;

    @Schema(description = "属性编码", required = true)
    private String code;

    @Schema(description = "属性名称", required = true)
    private String name;

    @Schema(description = "属性描述")
    private String description;

    @Schema(description = "属性数据类型")
    private String dataType; // ENUM('string', 'integer', 'decimal', 'boolean', 'date', 'datetime', 'enum', 'text')

    @Schema(description = "是否必填")
    private Boolean isRequired;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "选项值（适用于枚举类型）")
    private String options; // JSON type, mapped as String for now

    @Schema(description = "状态")
    private String status; // ENUM('active', 'inactive')

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}