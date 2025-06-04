package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.xym.convert.DeviceProtocolConvert;
import top.xym.dao.DeviceProtocolDao;
import top.xym.dto.DeviceProtocolDTO;
import top.xym.entity.DeviceProtocolEntity;
import top.xym.framework.mybatis.service.impl.BaseServiceImpl;
import top.xym.service.DeviceProtocolService;
import top.xym.vo.DeviceProtocolVO;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceProtocolServiceImpl extends BaseServiceImpl<DeviceProtocolDao, DeviceProtocolEntity> implements DeviceProtocolService {

    @Override
    public void save(DeviceProtocolDTO dto) {
        DeviceProtocolEntity entity = DeviceProtocolConvert.INSTANCE.convert(dto);
        // bindTime 会通过 MyBatis Plus 自动填充
        baseMapper.insert(entity);
    }

    @Override
    public void updateDevice(Integer id, Integer deviceId) {
        DeviceProtocolEntity entity = baseMapper.selectById(id);
        if (entity != null) {
            entity.setDeviceId(deviceId);
            baseMapper.updateById(entity);
        }
    }

    @Override
    public void updateProtocol(Integer id, Integer protocolId) {
        DeviceProtocolEntity entity = baseMapper.selectById(id);
        if (entity != null) {
            entity.setProtocolId(protocolId);
            baseMapper.updateById(entity);
        }
    }

    @Override
    public void deleteByDeviceId(Integer deviceId) {
        LambdaQueryWrapper<DeviceProtocolEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DeviceProtocolEntity::getDeviceId, deviceId);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByProtocolId(Integer protocolId) {
        LambdaQueryWrapper<DeviceProtocolEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DeviceProtocolEntity::getProtocolId, protocolId);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public List<DeviceProtocolVO> listAll() {
        List<DeviceProtocolEntity> list = baseMapper.selectList(null);
        return DeviceProtocolConvert.INSTANCE.convertList(list);
    }

    @Override
    public List<DeviceProtocolVO> listByDeviceId(Integer deviceId) {
        LambdaQueryWrapper<DeviceProtocolEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DeviceProtocolEntity::getDeviceId, deviceId);
        List<DeviceProtocolEntity> list = baseMapper.selectList(queryWrapper);
        return DeviceProtocolConvert.INSTANCE.convertList(list);
    }

    @Override
    public List<DeviceProtocolVO> listByProtocolId(Integer protocolId) {
        LambdaQueryWrapper<DeviceProtocolEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DeviceProtocolEntity::getProtocolId, protocolId);
        List<DeviceProtocolEntity> list = baseMapper.selectList(queryWrapper);
        return DeviceProtocolConvert.INSTANCE.convertList(list);
    }

    @Override
    public DeviceProtocolVO getById(Integer id) {
        DeviceProtocolEntity entity = baseMapper.selectById(id);
        return DeviceProtocolConvert.INSTANCE.convert(entity);
    }
}