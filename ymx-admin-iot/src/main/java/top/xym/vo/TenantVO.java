package top.xym.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "租户视图对象")
public class TenantVO {

    @Schema(description = "租户ID")
    private Integer id;

    @Schema(description = "租户类型ID")
    private Integer typeId;

    // 可以考虑在这里加入 typeName 等关联信息，如果需要的话
    // @Schema(description = "租户类型名称")
    // private String typeName;

    @Schema(description = "租户名称")
    private String name;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "设备总数")
    private Integer deviceCount;

    @Schema(description = "备注信息")
    private String remark;

    // @Schema(description = "地理位置坐标")
    // private String location;

    @Schema(description = "行政区域编码")
    private String regionCode;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}