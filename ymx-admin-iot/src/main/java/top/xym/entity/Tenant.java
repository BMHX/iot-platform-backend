package top.xym.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tenants")
@Schema(description = "租户主表")
public class Tenant extends BaseEntity {

    @Schema(description = "租户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "租户类型ID", required = true)
    private Integer typeId;

    @Schema(description = "租户名称", required = true)
    private String name;

    @Schema(description = "地址", required = true)
    private String address;

    @Schema(description = "联系人", required = true)
    private String contactPerson;

    @Schema(description = "联系电话", required = true)
    private String contactPhone;

    @Schema(description = "状态 active, inactive")
    private String status;

    @Schema(description = "设备总数")
    private Integer deviceCount;

    @Schema(description = "备注信息")
    private String remark;

    // @Schema(description = "地理位置坐标") // POINT类型特殊处理，暂时注释，可使用String或自定义TypeHandler
    // private String location;

    @Schema(description = "行政区域编码")
    private String regionCode;

}