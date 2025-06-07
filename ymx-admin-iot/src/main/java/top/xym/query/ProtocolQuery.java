package top.xym.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;

/**
 * 协议查询对象
 *
 * @author TraeAI
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "协议查询对象")
public class ProtocolQuery extends Query {

    @Schema(description = "关键字（协议名称、类型）")
    private String keyword;

    @Schema(description = "协议类型")
    private String type;

    @Schema(description = "协议状态：enabled-启用，disabled-禁用")
    private String status;
}
