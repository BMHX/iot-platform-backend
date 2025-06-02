package top.xym.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query; // Assuming you have a base Query class

@Data
@EqualsAndHashCode(callSuper = true) // If extending a base Query class with fields
@Schema(description = "管理员查询参数")
public class AdminQuery extends Query { // Extend your common Query class if available

    @Schema(description = "用户名 (模糊查询)")
    private String username;

    @Schema(description = "身份标识")
    private String identity;

    // Add other query parameters as needed, e.g., date ranges
}