package top.xym.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String mobile;
    private String nickname;
    private String avatar;
    private String email;
    private Integer gender;
    private Integer status;
}