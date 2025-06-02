package top.xym.dao;

import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.Device;
import top.xym.framework.mybatis.dao.BaseDao;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeviceDao extends BaseDao<Device> {
    List<Device> getList(Map<String, Object> params);
}