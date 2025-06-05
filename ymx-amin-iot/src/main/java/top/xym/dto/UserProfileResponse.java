package top.xym.dto;

import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    private Long userId; // 假设用户表 id 是 Long 类型，如果不是请更正
    private String username;
    private List<Integer> permissions;
    private PackageInfo currentPackage; // 新增字段
}
