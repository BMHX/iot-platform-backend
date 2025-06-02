package top.xym.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.AdminEntity;

@Mapper
public interface AdminMapper extends BaseMapper<AdminEntity> {
    // You can add custom SQL methods here if needed
}
