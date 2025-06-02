package top.xym.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.Devices; // 注意实体类名为 Devices

/**
 * 设备表 DAO
 *
 * @author YourName
 * @since YourDate
 */
@Mapper
public interface DeviceDao extends BaseMapper<Devices> {

}
