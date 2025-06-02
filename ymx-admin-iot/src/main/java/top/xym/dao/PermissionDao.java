package top.xym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.Permission;

/**
 * 权限表 DAO
 *
 * @author YourName
 * @since YourDate
 */
@Mapper
public interface PermissionDao extends BaseMapper<Permission> {

}
