package top.xym.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private Integer identity; // 假设 0 普通用户, 1 管理员等
    private Long permissionId; // 或者更详细的权限信息
    private Date createdtime;

    // 可以根据需要添加更多字段，比如昵称、头像URL等
    // 重要的是不包含密码等敏感信息
}
