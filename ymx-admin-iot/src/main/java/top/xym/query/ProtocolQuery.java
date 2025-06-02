package top.xym.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 协议表查询参数
 *
 * @author TraeAI
 * @since 2024-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "协议查询参数")
public class ProtocolQuery extends Query {

    @Schema(description = "协议名称，支持模糊查询")
    private String protocolName;

    @Schema(description = "协议编码")
    private String protocolCode;

    @Schema(description = "状态")
    private Integer status;
}
