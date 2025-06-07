package top.xym.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 版本VO
 *
 * @author TraeAI
 */
@Data
@Schema(description = "版本VO")
public class VersionVO {

    @Schema(description = "版本ID")
    private Integer id;

    @Schema(description = "版本号")
    private String version;

    @Schema(description = "版本类型：stable-正式版，beta-测试版，alpha-内测版")
    private String type;

    @Schema(description = "版本描述")
    private String description;

    @Schema(description = "更新内容，支持Markdown格式")
    private String updateContent;

    @Schema(description = "发布人")
    private String publisher;

    @Schema(description = "发布时间")
    private LocalDateTime publishTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "是否为当前激活版本")
    private Boolean isActive;
} 