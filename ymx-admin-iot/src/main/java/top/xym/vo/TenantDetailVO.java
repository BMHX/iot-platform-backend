package top.xym.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "租户详细信息视图对象")
public class TenantDetailVO {

    @Schema(description = "租户ID")
    private Integer id;

    @Schema(description = "租户名称")
    private String name;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "创建时间")
    private String createTime; // 注意：SQL中已格式化为字符串

    @Schema(description = "状态")
    private String status;

    @Schema(description = "类型编码")
    private String typeCode;

    @Schema(description = "扩展属性列表")
    private String attributes;

    @Data
    @Schema(description = "属性详情")
    public static class AttributeDetail {
        @Schema(description = "属性编码")
        private String code;

        @Schema(description = "属性名称")
        private String name;

        @Schema(description = "属性值")
        private String value; // JSON value, might need further parsing depending on usage
    }
}