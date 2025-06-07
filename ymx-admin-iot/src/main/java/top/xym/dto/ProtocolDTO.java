package top.xym.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 协议DTO
 *
 * @author TraeAI
 */
@Data
@Schema(description = "协议DTO")
public class ProtocolDTO {

    @Schema(description = "协议ID")
    private Integer id;

    @NotBlank(message = "协议名称不能为空")
    @Schema(description = "协议名称", required = true)
    private String name;

    @NotBlank(message = "协议类型不能为空")
    @Schema(description = "协议类型", required = true)
    private String type;

    @NotBlank(message = "协议版本不能为空")
    @Schema(description = "协议版本", required = true)
    private String version;

    @Schema(description = "协议描述")
    private String description;

    @Schema(description = "协议状态：enabled-启用，disabled-禁用")
    private String status;

    @Schema(description = "协议配置，JSON格式")
    private String config;
}
