package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.xym.convert.ProtocolConvert;
import top.xym.dao.ProtocolDao;
import top.xym.dto.ProtocolDTO;
import top.xym.entity.Protocol;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.ProtocolQuery;
import top.xym.service.ProtocolService;
import top.xym.vo.ProtocolVO;

import java.util.List;

/**
 * 协议表服务实现类
 *
 * @author TraeAI
 */
@Service
@AllArgsConstructor
public class ProtocolServiceImpl implements ProtocolService {

    private final ProtocolDao protocolDao;

    @Override
    public PageResult<ProtocolVO> getProtocolPage(ProtocolQuery query) {
        // 使用 query.getPage() 和 query.getLimit() (根据您之前确认的Query基类字段)
        Page<Protocol> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<Protocol> wrapper = buildQueryWrapper(query);
        protocolDao.selectPage(page, wrapper);
        return new PageResult<>(ProtocolConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    @Override
    public List<ProtocolVO> getProtocolList(ProtocolQuery query) {
        LambdaQueryWrapper<Protocol> wrapper = buildQueryWrapper(query);
        List<Protocol> protocols = protocolDao.selectList(wrapper);
        return ProtocolConvert.INSTANCE.convertList(protocols);
    }

    @Override
    public ProtocolVO getProtocolById(Integer id) { // 如果实体类ID是Long，这里改为Long
        Protocol protocol = protocolDao.selectById(id);
        return ProtocolConvert.INSTANCE.convert(protocol);
    }

    private LambdaQueryWrapper<Protocol> buildQueryWrapper(ProtocolQuery query) {
        LambdaQueryWrapper<Protocol> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.hasText(query.getProtocolName())) {
            wrapper.like(Protocol::getProtocolName, query.getProtocolName());
        }
        if (StringUtils.hasText(query.getProtocolCode())) {
            wrapper.eq(Protocol::getProtocolCode, query.getProtocolCode());
        }
        if (query.getStatus() != null) {
            wrapper.eq(Protocol::getStatus, query.getStatus());
        }
        // 默认排序，可以根据需要调整或移除
        wrapper.orderByDesc(Protocol::getId);
        return wrapper;
    }
}