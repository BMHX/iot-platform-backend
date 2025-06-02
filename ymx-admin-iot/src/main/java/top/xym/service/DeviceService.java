package top.xym.service;

import top.xym.framework.common.utils.PageResult; // 假设您有通用的PageResult
import top.xym.query.DeviceQuery;
import top.xym.vo.DeviceVO;
// import top.xym.dto.DeviceDTO; // 如果有新增修改等操作再引入

import java.util.List;

/**
 * 设备表 服务接口
 *
 * @author YourName
 * @since YourDate
 */
public interface DeviceService {

    /**
     * 分页查询设备信息
     *
     * @param query 查询参数
     * @return 分页结果
     */
    PageResult<DeviceVO> page(DeviceQuery query);

    /**
     * 查询所有设备信息
     *
     * @return 设备列表
     */
    List<DeviceVO> listAll();

    // 如果需要增删改查，后续可以添加 save, update, delete, getById 等方法
}