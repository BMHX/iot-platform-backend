package top.xym.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "管理员详细信息VO")
public class AdminInfoVO {

    @Schema(description = "管理员ID")
    private Integer id;

    @Schema(description = "用户名")
    private String username;

    // Password should generally not be included in VOs
    // private String password;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "身份标识")
    private String identity;

    @Schema(description = "权限套餐ID")
    private Integer permissionId;

}
