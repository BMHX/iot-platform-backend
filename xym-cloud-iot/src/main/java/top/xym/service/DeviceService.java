package top.xym.service;

import top.xym.entity.Device;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.mybatis.service.BaseService;
import top.xym.query.DeviceQuery;
import top.xym.vo.DeviceVO;

public interface DeviceService extends BaseService<Device> {
    PageResult<DeviceVO> page(DeviceQuery query);
    /**
     * 发送命令
     *
     * @param deviceId 设备id
     * @param command 命令
     */
    void sendCommand(String deviceId, String command);
}