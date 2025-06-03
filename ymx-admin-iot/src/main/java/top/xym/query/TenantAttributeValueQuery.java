package top.xym.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;


@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "租户扩展属性值查询参数")
public class TenantAttributeValueQuery extends Query {

    @Schema(description = "租户ID")
    private Integer tenantId;

    @Schema(description = "属性定义ID")
    private Integer attrId;
}