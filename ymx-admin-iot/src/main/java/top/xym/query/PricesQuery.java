package top.xym.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.common.query.Query;

/**
 * 价格套餐表 查询
 *
 * @author YourName
 * @since YourDate
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PricesQuery extends Query {
    private String permissionName;
}
