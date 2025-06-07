package top.xym.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 版本DTO
 *
 * @author TraeAI
 */
@Data
@Schema(description = "版本DTO")
public class VersionDTO {

    @Schema(description = "版本ID")
    private Integer id;

    @NotBlank(message = "版本号不能为空")
    @Pattern(regexp = "^\\d+\\.\\d+\\.\\d+$", message = "版本号格式应为 x.y.z")
    @Schema(description = "版本号", required = true)
    private String version;

    @NotBlank(message = "版本类型不能为空")
    @Schema(description = "版本类型：stable-正式版，beta-测试版，alpha-内测版", required = true)
    private String type;

    @Schema(description = "版本描述")
    private String description;

    @Schema(description = "更新内容，支持Markdown格式")
    private String updateContent;

    @Schema(description = "发布人")
    private String publisher;

    @Schema(description = "是否为当前激活版本")
    private Boolean isActive;
} 