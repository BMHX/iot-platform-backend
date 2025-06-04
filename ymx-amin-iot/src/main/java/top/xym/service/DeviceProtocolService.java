package top.xym.service;

import top.xym.dto.DeviceProtocolDTO;
import top.xym.entity.DeviceProtocolEntity;
import top.xym.framework.mybatis.service.BaseService;
import top.xym.vo.DeviceProtocolVO;

import java.util.List;

public interface DeviceProtocolService extends BaseService<DeviceProtocolEntity> {

    void save(DeviceProtocolDTO dto);

    void updateDevice(Integer id, Integer deviceId);

    void updateProtocol(Integer id, Integer protocolId);

    void deleteByDeviceId(Integer deviceId);

    void deleteByProtocolId(Integer protocolId);

    List<DeviceProtocolVO> listAll();

    List<DeviceProtocolVO> listByDeviceId(Integer deviceId);

    List<DeviceProtocolVO> listByProtocolId(Integer protocolId);

    DeviceProtocolVO getById(Integer id);
}