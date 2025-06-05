package top.xym.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime; // Using LocalDateTime for datetime fields

@Data
@TableName("admin") // Specify the table name
public class AdminEntity {

    @TableId(type = IdType.AUTO) // Assuming id is auto-incremented by the database
    private Integer id;

    private String username;

    private String password;

    @TableField("createdtime")
    private LocalDateTime createdTime; // Consistent naming with your description

    private String identity;

    private Integer permissionId;

    private String name;

    private String email;

    private String phone;

    private String status;

    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
}
