package top.xym.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;


@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "租户扩展属性定义查询参数")
public class TenantAttributeDefinitionQuery extends Query {

    @Schema(description = "租户类型ID")
    private Integer typeId;

    @Schema(description = "属性编码")
    private String code;

    @Schema(description = "属性名称")
    private String name;

    @Schema(description = "属性数据类型")
    private String dataType;

    @Schema(description = "状态")
    private String status;
}