package top.xym.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String username;  // 管理员用户名
    private String password;  // 管理员密码
    private String identity;  // 管理员身份
    private Integer permissionId; // 权限套餐ID
}