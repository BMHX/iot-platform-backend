package top.xym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.Admin;

@Mapper
public interface AdminDao extends BaseMapper<Admin> {
}