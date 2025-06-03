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
@TableName("tenant_attribute_values")
@Schema(description = "租户扩展属性值表")
public class TenantAttributeValue {

    @Schema(description = "属性值ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "租户ID", required = true)
    private Integer tenantId;

    @Schema(description = "属性定义ID", required = true)
    private Integer attrId;

    @Schema(description = "属性值", required = true)
    private String value; // JSON type, mapped as String for now

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
