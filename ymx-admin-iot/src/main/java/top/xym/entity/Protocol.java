package top.xym.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.mybatis.entity.BaseEntity;

/**
 * 协议表
 *
 * @author TraeAI
 * @since 2024-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("protocol")
public class Protocol extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 协议名称
     */
    private String protocolName;

    /**
     * 协议编码
     */
    private String protocolCode;

    /**
     * 协议版本
     */
    private String version;

    /**
     * 协议描述
     */
    private String description;

    /**
     * 状态 (例如: 0-禁用, 1-启用)
     */
    private Integer status;
    
    /**
     * 协议配置，JSON格式
     */
    private String config;

    // createTime 和 updateTime 字段通常由 BaseEntity 提供，如果您的 BaseEntity 不包含它们，请取消注释以下代码
    /*
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    */
}