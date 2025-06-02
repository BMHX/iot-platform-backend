package top.xym.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;

/**
 * 权限表 查询
 *
 * @author YourName
 * @since YourDate
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionQuery extends Query {
    private String name;
}
