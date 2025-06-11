package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.xym.dao.PriceTimeDao;
import top.xym.entity.PriceTime;
import top.xym.service.PriceTimeService;

import java.time.LocalDateTime;

/**
 * 管理员套餐到期时间 服务实现
 */
@Service
@AllArgsConstructor
public class PriceTimeServiceImpl implements PriceTimeService {

    private final PriceTimeDao priceTimeDao;

    @Override
    public PriceTime getByAdminId(Long adminId) {
        LambdaQueryWrapper<PriceTime> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PriceTime::getAdminId, adminId);
        return priceTimeDao.selectOne(wrapper);
    }

    @Override
    public void setDueTime(Long adminId, LocalDateTime dueTime) {
        // 检查是否已存在记录
        PriceTime existingRecord = getByAdminId(adminId);
        
        if (existingRecord == null) {
            // 创建新记录
            PriceTime priceTime = new PriceTime();
            priceTime.setAdminId(adminId);
            priceTime.setDueTime(dueTime);
            priceTime.setCreatedAt(LocalDateTime.now());
            priceTime.setUpdatedAt(LocalDateTime.now());
            priceTimeDao.insert(priceTime);
        } else {
            // 更新现有记录
            updateDueTime(adminId, dueTime);
        }
    }

    @Override
    public void updateDueTime(Long adminId, LocalDateTime dueTime) {
        PriceTime priceTime = getByAdminId(adminId);
        if (priceTime != null) {
            priceTime.setDueTime(dueTime);
            priceTime.setUpdatedAt(LocalDateTime.now());
            priceTimeDao.updateById(priceTime);
        }
    }

    @Override
    public void deleteByAdminId(Long adminId) {
        LambdaQueryWrapper<PriceTime> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PriceTime::getAdminId, adminId);
        priceTimeDao.delete(wrapper);
    }
} 