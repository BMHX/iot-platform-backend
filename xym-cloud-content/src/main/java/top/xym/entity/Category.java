package top.xym.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_category")
public class Category extends BaseEntity {
    private String name;
    private String cover;
}