package top.xym.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 协议表DTO
 *
 * @author TraeAI
 * @since 2024-07-30
 */
@Data
@Schema(description = "协议信息数据传输对象")
public class ProtocolDTO {

    @Schema(description = "协议ID，更新时需要")
    private Long id;

    @NotEmpty(message = "协议名称不能为空")
    @Schema(description = "协议名称", required = true)
    private String protocolName;

    @NotEmpty(message = "协议编码不能为空")
    @Schema(description = "协议编码", required = true)
    private String protocolCode;

    @NotEmpty(message = "协议版本不能为空")
    @Schema(description = "协议版本", required = true)
    private String version;

    @Schema(description = "协议描述")
    private String description;

    @NotNull(message = "状态不能为空")
    @Schema(description = "状态 (例如: 0-禁用, 1-启用)", required = true)
    private Integer status;
}
