package top.xym.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "租户扩展属性值数据传输对象")
public class TenantAttributeValueDTO {

    @Schema(description = "属性值ID")
    private Integer id;

    @Schema(description = "租户ID", required = true)
    private Integer tenantId;

    @Schema(description = "属性定义ID", required = true)
    private Integer attrId;

    @Schema(description = "属性值", required = true)
    private String value;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
