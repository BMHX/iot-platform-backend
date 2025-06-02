package top.xym.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.mybatis.entity.BaseEntity;

import java.time.LocalDateTime;

/**
 * 管理员表
 */
@Data
@EqualsAndHashCode(callSuper = true) // 注意：如果BaseEntity中没有合适的equals/hashCode，可能需要调整
@TableName("admin")
public class Admin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 身份标识
     */
    private String identity; // 假设数据库中列名为 identity

    /**
     * 创建时间
     * 将 createTime 映射到数据库的 createdtime 字段
     * 并设置为插入时自动填充
     */
    @TableField(value = "createdtime", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 权限套餐ID (对应 prices 表的 id)
     */
    private Integer permissionId;

    /**
     * 更新时间
     * 标记为数据库表中不存在此字段
     */
    @TableField(exist = false)
    private LocalDateTime updateTime;

    /**
     * 删除标志 (0未删除 1已删除)
     * 标记为数据库表中不存在此字段
     * 注意：如果 BaseEntity 中的 deleted 字段有 @TableLogic 注解，
     * 覆盖它并设置为 exist = false 将禁用逻辑删除功能。
     */
    @TableField(exist = false)
    private Integer deleted;

    // 如果 BaseEntity 中还有其他字段，而 admin 表中不存在，也需要类似处理
    // 例如：
    // @TableField(exist = false)
    // private String createBy;
    // @TableField(exist = false)
    // private String updateBy;
}
