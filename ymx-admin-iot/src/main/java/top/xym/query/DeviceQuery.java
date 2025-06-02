package top.xym.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query; // 假设您有通用的Query基类

/**
 * 设备表 查询
 *
 * @author YourName
 * @since YourDate
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeviceQuery extends Query {
    // 暂时不需要特定查询字段，如果需要按条件查询再添加
    // private String deviceName;
    // private Integer userId;
}
