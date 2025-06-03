package top.xym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "租户类型数据传输对象")
public class TenantTypeDTO {

    @Schema(description = "类型ID")
    private Integer id;

    @NotBlank(message = "类型编码不能为空")
    @Size(max = 50, message = "类型编码长度不能超过50个字符")
    @Schema(description = "类型编码", required = true)
    private String code;

    @NotBlank(message = "类型名称不能为空")
    @Size(max = 100, message = "类型名称长度不能超过100个字符")
    @Schema(description = "类型名称", required = true)
    private String name;

    @Schema(description = "类型描述")
    private String description;

    @Size(max = 100, message = "图标路径长度不能超过100个字符")
    @Schema(description = "图标")
    private String icon;

    @Schema(description = "状态 (active, inactive)", defaultValue = "active")
    private String status;
}