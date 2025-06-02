package top.xym.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xym.framework.mybatis.entity.BaseEntity;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_note",autoResultMap = true)
public class Note extends BaseEntity {
    private Long userId;
    private Long categoryId;
    private String title;
    private String content;
    private String cover;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> tags;
}