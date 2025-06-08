package top.xym.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.mybatis.entity.BaseEntity;

/**
 * 版本表
 *
 * @author TraeAI
 * @since 2024-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("version")
public class Version extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 版本号
     */
    private String version;

    /**
     * 版本类型 (stable: 正式版, beta: 测试版, alpha: 内测版)
     */
    private String type;

    /**
     * 版本描述
     */
    private String description;

    /**
     * 更新内容 (支持Markdown格式)
     */
    private String updateContent;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 是否为当前激活版本
     */
    private Boolean isActive;

    // createTime 和 updateTime 字段通常由 BaseEntity 提供
} 