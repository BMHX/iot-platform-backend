package top.xym.dto;

import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageInfo {
    private Integer id; // 套餐ID
    private String name; // 套餐名称
    private Integer price; // 套餐价格
    private List<Integer> permissions; // 包含的权限ID列表
}
