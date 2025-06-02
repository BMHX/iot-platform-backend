package top.xym.framework.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import top.xym.framework.security.user.SecurityUser;
import top.xym.framework.security.user.UserDetail;

import java.time.LocalDateTime;

/**
 * mybatis-plus 自动填充字段
 *
 * @author mqxu
 */
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_TIME = "createTime";
    private final static String UPDATE_TIME = "updateTime";
    private final static String DELETED = "deleted";

    @Override
    public void insertFill(MetaObject metaObject) {
        UserDetail user = SecurityUser.getUser();
        LocalDateTime now = LocalDateTime.now();
        // 创建时间
        setFieldValByName(CREATE_TIME, now, metaObject);
        // 更新时间
        setFieldValByName(UPDATE_TIME, now, metaObject);
        // 删除标识
        setFieldValByName(DELETED, 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
    }
}