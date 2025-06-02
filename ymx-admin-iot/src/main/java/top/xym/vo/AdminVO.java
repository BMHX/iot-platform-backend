package top.xym.vo;

import lombok.Data;

@Data
public class AdminVO {
    private Long id;
    private String username;  // 管理员用户名
    private String identity;  // 管理员身份
}
