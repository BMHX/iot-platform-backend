package top.xym.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "管理员DTO，用于新增或通用更新")
public class AdminDTO {

    @Schema(description = "管理员ID (更新时需要)")
    private Integer id; // Nullable for save, NotNull for update

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码 (新增时需要，更新时可选)")
    private String password; // Consider making this mandatory for save, optional for update

    @Schema(description = "身份标识")
    private String identity;

    @Schema(description = "权限套餐ID")
    private Integer permissionId;
}
