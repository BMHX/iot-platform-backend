package top.xym.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;

/**
 * 版本查询对象
 *
 * @author TraeAI
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "版本查询对象")
public class VersionQuery extends Query {

    @Schema(description = "版本号")
    private String version;

    @Schema(description = "版本类型：stable-正式版，beta-测试版，alpha-内测版")
    private String type;

    @Schema(description = "是否为当前激活版本")
    private Boolean isActive;
} 