package top.xym.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "租户扩展属性定义视图对象")
public class TenantAttributeDefinitionVO {

    @Schema(description = "属性定义ID")
    private Integer id;

    @Schema(description = "租户类型ID")
    private Integer typeId;

    @Schema(description = "属性编码")
    private String code;

    @Schema(description = "属性名称")
    private String name;

    @Schema(description = "属性描述")
    private String description;

    @Schema(description = "属性数据类型")
    private String dataType;

    @Schema(description = "是否必填")
    private Boolean isRequired;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "选项值（适用于枚举类型）")
    private String options;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}