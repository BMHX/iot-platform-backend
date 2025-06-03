package top.xym.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "租户数据传输对象")
public class TenantDTO {

    @Schema(description = "租户ID (更新时需要)")
    private Integer id;

    @NotNull(message = "租户类型ID不能为空")
    @Schema(description = "租户类型ID", required = true)
    private Integer typeId;

    @NotBlank(message = "租户名称不能为空")
    @Size(max = 100, message = "租户名称长度不能超过100个字符")
    @Schema(description = "租户名称", required = true)
    private String name;

    @NotBlank(message = "地址不能为空")
    @Size(max = 255, message = "地址长度不能超过255个字符")
    @Schema(description = "地址", required = true)
    private String address;

    @NotBlank(message = "联系人不能为空")
    @Size(max = 50, message = "联系人长度不能超过50个字符")
    @Schema(description = "联系人", required = true)
    private String contactPerson;

    @NotBlank(message = "联系电话不能为空")
    @Size(max = 20, message = "联系电话长度不能超过20个字符")
    @Schema(description = "联系电话", required = true)
    private String contactPhone;

    @Schema(description = "状态 (active, inactive)")
    private String status;

    @Schema(description = "设备总数")
    private Integer deviceCount;

    @Schema(description = "备注信息")
    private String remark;

    // @Schema(description = "地理位置坐标")
    // private String location; // POINT类型特殊处理

    @Schema(description = "行政区域编码")
    @Size(max = 20, message = "行政区域编码长度不能超过20个字符")
    private String regionCode;
}