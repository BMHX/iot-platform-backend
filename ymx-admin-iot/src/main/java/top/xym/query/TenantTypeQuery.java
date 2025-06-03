package top.xym.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "租户类型查询参数")
public class TenantTypeQuery extends Query {

    @Schema(description = "类型编码")
    private String code;

    @Schema(description = "类型名称")
    private String name;

    @Schema(description = "状态 (active, inactive)")
    private String status;
}