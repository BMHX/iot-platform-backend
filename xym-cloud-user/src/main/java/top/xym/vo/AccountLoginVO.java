package top.xym.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * @author xym
 **/
@Data
@Schema(description = "账号登录vo")
public class AccountLoginVO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "账号")
    private String username;
    @Schema(description = "accessToken")
    private String accessToken;
}