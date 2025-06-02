package top.xym.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "名称查询")
public class CategoryQuery extends Query {
    @Schema(description = "名称")
    private String name;
}