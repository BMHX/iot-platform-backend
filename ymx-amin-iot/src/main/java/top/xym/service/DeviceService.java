package top.xym.service;

import top.xym.entity.DeviceEntity;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.mybatis.service.BaseService;
import top.xym.query.DeviceQuery;
import top.xym.vo.DeviceVO;


import java.util.List;

public interface DeviceService extends BaseService<DeviceEntity> {
    PageResult<DeviceVO> page(DeviceQuery query);

    void save(DeviceVO vo);

    void update(DeviceVO vo);

    void delete(Integer id);

    DeviceVO getById(Integer id);
    void updateDeviceIntegration(Integer id, Integer integrationValue);
    List<DeviceVO> listByAdminId(Integer adminId);
}