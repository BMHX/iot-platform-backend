package top.xym.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "租户查询参数")
public class TenantQuery extends Query {

    @Schema(description = "租户类型ID")
    private Integer typeId;

    @Schema(description = "租户名称 (模糊查询)")
    private String name;

    @Schema(description = "联系人 (模糊查询)")
    private String contactPerson;

    @Schema(description = "联系电话 (模糊查询)")
    private String contactPhone;

    @Schema(description = "状态 (active, inactive)")
    private String status;

    @Schema(description = "行政区域编码")
    private String regionCode;
}