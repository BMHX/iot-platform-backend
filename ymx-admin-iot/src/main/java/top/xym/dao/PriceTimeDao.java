package top.xym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.PriceTime;

/**
 * 管理员套餐到期时间表 DAO
 */
@Mapper
public interface PriceTimeDao extends BaseMapper<PriceTime> {
} 