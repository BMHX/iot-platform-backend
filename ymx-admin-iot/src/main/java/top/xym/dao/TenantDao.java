package top.xym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.xym.entity.Tenant;

import java.util.List;
import java.util.Map;

/**
 * 租户表 DAO
 */
@Mapper
public interface TenantDao extends BaseMapper<Tenant> {

    /**
     * 获取租户类型分布
     * @return 租户类型分布数据
     */
    @Select("SELECT tt.name as name, COUNT(t.id) as value " +
            "FROM tenants t " +
            "JOIN tenant_types tt ON t.type_id = tt.id " +
            "GROUP BY tt.name")
    List<Map<String, Object>> getTenantTypeDistribution();
    
    /**
     * 获取租户区域分布
     * @return 租户区域分布数据
     */
    @Select("SELECT region_code as code, COUNT(*) as count FROM tenants WHERE region_code IS NOT NULL GROUP BY region_code")
    List<Map<String, Object>> getTenantRegionDistribution();
} 