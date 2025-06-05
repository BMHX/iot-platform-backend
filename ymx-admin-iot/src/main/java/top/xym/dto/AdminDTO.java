package top.xym.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String username;  // 管理员用户名
    private String password;  // 管理员密码
    private String identity;  // 管理员身份
    private Integer permissionId; // 权限套餐ID
    private String name;      // 管理员姓名
    private String email;     // 管理员邮箱
    private String phone;     // 管理员手机号
    private String status;    // 管理员状态
}