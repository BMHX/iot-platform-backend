package top.xym.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "管理员更新DTO")
public class AdminUpdateDTO {

    @Schema(description = "管理员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "管理员ID不能为空")
    private Integer id;

    @Schema(description = "新用户名 (可选)")
    private String username; // Optional: only provide if username needs to be changed

    // For password change, it's common to ask for current password for verification
    @Schema(description = "当前密码 (用于修改密码时验证)")
    private String currentPassword;

    @Schema(description = "新密码 (可选)")
    private String newPassword; // Optional: only provide if password needs to be changed

    @Schema(description = "身份标识 (可选)")
    private String identity;

    @Schema(description = "权限套餐ID (可选)")
    private Integer permissionId;
}
