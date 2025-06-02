package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl; // 引入ServiceImpl
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
// import org.springframework.util.StringUtils; // 如果查询条件中需要判断字符串
import top.xym.convert.DeviceConvert;
import top.xym.dao.DeviceDao;
import top.xym.entity.Devices; // 注意实体类名为 Devices
import top.xym.framework.common.utils.PageResult;
import top.xym.query.DeviceQuery;
import top.xym.service.DeviceService;
import top.xym.vo.DeviceVO;

import java.util.List;

/**
 * 设备表 服务实现
 *
 * @author YourName
 * @since YourDate
 */
@Service
@AllArgsConstructor
public class DeviceServiceImpl extends ServiceImpl<DeviceDao, Devices> implements DeviceService { // 继承ServiceImpl

    // private final DeviceDao deviceDao; // ServiceImpl已包含baseMapper，如果不需要额外方法可不注入

    @Override
    public PageResult<DeviceVO> page(DeviceQuery query) {
        IPage<Devices> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<Devices> wrapper = new LambdaQueryWrapper<>();
        // 根据DeviceQuery中的字段添加查询条件，例如：
        // if (StringUtils.hasText(query.getDeviceName())) {
        //     wrapper.like(Devices::getDeviceName, query.getDeviceName());
        // }
        wrapper.orderByDesc(Devices::getId); // 默认按ID降序
        IPage<Devices> resultPage = this.baseMapper.selectPage(page, wrapper); // 使用this.baseMapper
        return new PageResult<>(DeviceConvert.INSTANCE.convertList(resultPage.getRecords()), resultPage.getTotal());
    }

    @Override
    public List<DeviceVO> listAll() {
        List<Devices> list = this.baseMapper.selectList(null); // 使用this.baseMapper
        return DeviceConvert.INSTANCE.convertList(list);
    }
}
