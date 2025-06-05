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

    @Schema(description = "密码")
    private String password;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "身份标识")
    private String identity;

    @Schema(description = "权限套餐ID")
    private Integer permissionId;

    @Schema(description = "真实姓名")
    private String name;

    @Schema(description = "电子邮件")
    private String email;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @Schema(description = "权限名称")
    private String permissionName;

    @Schema(description = "token")
    private String token;
}
