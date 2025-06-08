package top.xym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.Version;

/**
 * 版本表 DAO
 *
 * @author TraeAI
 * @since 2024-07-30
 */
@Mapper
public interface VersionDao extends BaseMapper<Version> {
} 