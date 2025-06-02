package top.xym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 价格套餐表
 *
 * @author YourName
 * @since YourDate
 */
@Data
@TableName("prices")
public class Prices implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 套餐名称
     */
    private String permissionName; // 对应表字段 permission_name

    /**
     * 价格
     */
    private Integer price;

    /**
     * 套餐内容 (存储permission表的id, 例如: "{1,2,3,4}")
     */
    private String card;

}
