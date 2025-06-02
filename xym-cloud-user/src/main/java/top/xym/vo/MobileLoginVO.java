package top.xym.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "⼿机号登录vo")
public class MobileLoginVO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "⼿机号")
    private String mobile;
    @Schema(description = "accessToken")
    private String accessToken;
}