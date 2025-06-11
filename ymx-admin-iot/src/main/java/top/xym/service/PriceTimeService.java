package top.xym.service;

import top.xym.entity.PriceTime;

import java.time.LocalDateTime;

/**
 * 管理员套餐到期时间 服务接口
 */
public interface PriceTimeService {

    /**
     * 获取管理员的套餐到期时间
     *
     * @param adminId 管理员ID
     * @return 套餐到期时间对象，如果不存在则返回null
     */
    PriceTime getByAdminId(Long adminId);

    /**
     * 设置管理员的套餐到期时间
     *
     * @param adminId 管理员ID
     * @param dueTime 到期时间
     */
    void setDueTime(Long adminId, LocalDateTime dueTime);

    /**
     * 更新管理员的套餐到期时间
     *
     * @param adminId 管理员ID
     * @param dueTime 新的到期时间
     */
    void updateDueTime(Long adminId, LocalDateTime dueTime);

    /**
     * 删除管理员的套餐到期时间
     *
     * @param adminId 管理员ID
     */
    void deleteByAdminId(Long adminId);
} 