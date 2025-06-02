package top.xym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 价格套餐表 数据传输对象
 *
 * @author YourName
 * @since YourDate
 */
@Data
public class PricesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "套餐名称不能为空")
    private String permissionName;

    // price 字段将由后端根据 card 内容计算，前端不需要传递

    @NotBlank(message = "套餐内容不能为空")
    @Pattern(regexp = "^\\{\\d+(,\\d+)*\\}$", message = "套餐内容格式不正确，应为 {1,2,3} 格式")
    private String card; // 前端传递如 "{1,2,3,4}" 的字符串

}
