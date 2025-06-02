package top.xym.vo;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 协议表VO
 *
 * @author TraeAI
 * @since 2024-07-30
 */
@Data
@Schema(description = "协议信息视图对象")
public class ProtocolVO {

    @Schema(description = "协议ID")
    private Long id;

    @Schema(description = "协议名称")
    private String protocolName;

    @Schema(description = "协议编码")
    private String protocolCode;

    @Schema(description = "协议版本")
    private String version;

    @Schema(description = "协议描述")
    private String description;

    @Schema(description = "状态 (例如: 0-禁用, 1-启用)")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
