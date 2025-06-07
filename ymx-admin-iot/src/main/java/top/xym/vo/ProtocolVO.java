package top.xym.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 协议VO
 *
 * @author TraeAI
 */
@Data
@Schema(description = "协议VO")
public class ProtocolVO {

    @Schema(description = "协议ID")
    private Integer id;

    @Schema(description = "协议名称")
    private String name;

    @Schema(description = "协议类型")
    private String type;

    @Schema(description = "协议版本")
    private String version;

    @Schema(description = "协议描述")
    private String description;

    @Schema(description = "协议状态：enabled-启用，disabled-禁用")
    private String status;

    @Schema(description = "协议配置，JSON格式")
    private String config;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    private String creator;
}
