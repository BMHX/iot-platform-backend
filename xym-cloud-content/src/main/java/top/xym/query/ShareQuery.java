package top.xym.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "标题查询")
public class ShareQuery extends Query {
    @Schema(description = "标题")
    private String title;
}