package top.xym.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "租户属性视图对象")
public class TenantAttributeVO {

    @Schema(description = "属性编码")
    private String code;

    @Schema(description = "属性名称")
    private String name;

    @Schema(description = "属性值")
    private String value; // Assuming value is stored as a string representation of JSON
}