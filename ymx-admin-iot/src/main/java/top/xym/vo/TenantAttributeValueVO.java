package top.xym.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "租户扩展属性值视图对象")
public class TenantAttributeValueVO {

    @Schema(description = "属性值ID")
    private Integer id;

    @Schema(description = "租户ID")
    private Integer tenantId;

    @Schema(description = "属性定义ID")
    private Integer attrId;

    @Schema(description = "属性值")
    private String value;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}