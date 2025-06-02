package top.xym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限表
 *
 * @author YourName
 * @since YourDate
 */
@Data
@TableName("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    private String name;

}
