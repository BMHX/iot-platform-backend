package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.xym.convert.DeviceConvert;
import top.xym.dao.DeviceDao;
import top.xym.entity.DeviceEntity;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.mybatis.service.impl.BaseServiceImpl;
import top.xym.query.DeviceQuery;
import top.xym.service.DeviceService;
import top.xym.vo.DeviceVO;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceServiceImpl extends BaseServiceImpl<DeviceDao, DeviceEntity> implements DeviceService {

    @Override
    public PageResult<DeviceVO> page(DeviceQuery query) {
        LambdaQueryWrapper<DeviceEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(query.getDeviceName() != null, DeviceEntity::getDeviceName, query.getDeviceName());
        queryWrapper.eq(query.getDeviceCode() != null, DeviceEntity::getDeviceCode, query.getDeviceCode());
        queryWrapper.eq(query.getStatus() != null, DeviceEntity::getStatus, query.getStatus());
        queryWrapper.eq(query.getDeviceType() != null, DeviceEntity::getDeviceType, query.getDeviceType());
        queryWrapper.eq(query.getAdminId() != null, DeviceEntity::getAdminId, query.getAdminId());
        queryWrapper.orderByDesc(DeviceEntity::getCreatedAt);

        List<DeviceEntity> list = list(queryWrapper);
        List<DeviceVO> voList = DeviceConvert.INSTANCE.convertList(list);
        return new PageResult<>(voList, voList.size());
    }

    @Override
    public void save(DeviceVO vo) {
        DeviceEntity entity = DeviceConvert.INSTANCE.convertVO(vo);
        baseMapper.insert(entity);
    }

    @Override
    public void update(DeviceVO vo) {
        DeviceEntity entity = DeviceConvert.INSTANCE.convertVO(vo);
        baseMapper.updateById(entity);
    }

    @Override
    public void delete(Integer id) { // 修改参数为单个 Integer
        baseMapper.deleteById(id); // 修改为按ID删除
    }

    @Override
    public DeviceVO getById(Integer id) {
        DeviceEntity entity = baseMapper.selectById(id);
        return DeviceConvert.INSTANCE.convert(entity);
    }

    @Override
    public void updateDeviceIntegration(Integer id, Integer integrationValue) {
        DeviceEntity entity = baseMapper.selectById(id);
        if (entity != null) {
            entity.setDeviceIntegration(integrationValue);
            baseMapper.updateById(entity);
        }
        // 您可以根据需要添加设备不存在时的处理逻辑，例如抛出异常
    }
    @Override
    public List<DeviceVO> listByAdminId(Integer adminId) {
        LambdaQueryWrapper<DeviceEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DeviceEntity::getAdminId, adminId);
        queryWrapper.orderByDesc(DeviceEntity::getCreatedAt);
        List<DeviceEntity> list = list(queryWrapper);
        return DeviceConvert.INSTANCE.convertList(list);
    }
}