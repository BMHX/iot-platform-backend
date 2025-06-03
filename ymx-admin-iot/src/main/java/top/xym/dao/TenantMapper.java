package top.xym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.Tenant;

@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {
    // 可以在这里添加自定义的SQL查询方法，如果需要的话
    // 例如，根据名称查询租户（已通过MyBatis-Plus Wrapper实现，通常不需要额外定义）
    // Tenant selectByName(String name);
}