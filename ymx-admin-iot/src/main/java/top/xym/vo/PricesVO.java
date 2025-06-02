package top.xym.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * 价格套餐表 值对象
 *
 * @author YourName
 * @since YourDate
 */
@Data
public class PricesVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String permissionName;

    private Integer price;

    private String card;

}
