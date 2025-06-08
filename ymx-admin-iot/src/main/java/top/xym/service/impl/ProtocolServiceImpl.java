package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.xym.convert.ProtocolConvert;
import top.xym.dao.ProtocolDao;
import top.xym.dto.ProtocolDTO;
import top.xym.entity.Protocol;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.ProtocolQuery;
import top.xym.service.ProtocolService;
import top.xym.vo.ProtocolVO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

/**
 * 协议表服务实现类
 *
 * @author TraeAI
 */
@Service
@AllArgsConstructor
public class ProtocolServiceImpl implements ProtocolService {

    private final ProtocolDao protocolDao;
    private final ObjectMapper objectMapper;

    @Override
    public PageResult<ProtocolVO> getProtocolPage(ProtocolQuery query) {
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
    public ProtocolVO getProtocolById(Integer id) {
        Protocol protocol = protocolDao.selectById(id);
        return protocol != null ? ProtocolConvert.INSTANCE.convert(protocol) : null;
    }

    private LambdaQueryWrapper<Protocol> buildQueryWrapper(ProtocolQuery query) {
        LambdaQueryWrapper<Protocol> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.hasText(query.getKeyword())) {
            wrapper.like(Protocol::getProtocolName, query.getKeyword())
                  .or()
                  .like(Protocol::getProtocolCode, query.getKeyword());
        }
        if (StringUtils.hasText(query.getType())) {
            wrapper.eq(Protocol::getProtocolCode, query.getType());
        }
        if (query.getStatus() != null) {
            wrapper.eq(Protocol::getStatus, query.getStatus());
        }
        // 默认排序
        wrapper.orderByDesc(Protocol::getId);
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createProtocol(ProtocolDTO protocolDTO) {
        Protocol protocol = ProtocolConvert.INSTANCE.convert(protocolDTO);
        
        // 设置默认值
        if (protocol.getStatus() == null) {
            protocol.setStatus(1); // 1表示启用
        }
        
        protocolDao.insert(protocol);
        return protocol.getId().intValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProtocol(Integer id, ProtocolDTO protocolDTO) {
        Protocol protocol = protocolDao.selectById(id);
        if (protocol == null) {
            throw new RuntimeException("协议不存在");
        }
        
        Protocol updateProtocol = ProtocolConvert.INSTANCE.convert(protocolDTO);
        updateProtocol.setId(Long.valueOf(id));
        
        protocolDao.updateById(updateProtocol);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProtocol(Integer id) {
        protocolDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteProtocol(List<Integer> ids) {
        protocolDao.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProtocolStatus(Integer id, Integer status) {
        Protocol protocol = protocolDao.selectById(id);
        if (protocol == null) {
            throw new RuntimeException("协议不存在");
        }
        
        Protocol updateProtocol = new Protocol();
        updateProtocol.setId(Long.valueOf(id));
        updateProtocol.setStatus(status);
        
        protocolDao.updateById(updateProtocol);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProtocolConfig(Integer id, Object config) {
        Protocol protocol = protocolDao.selectById(id);
        if (protocol == null) {
            throw new RuntimeException("协议不存在");
        }
        
        try {
            // 将配置对象转换为JSON字符串，使用更严格的配置
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT, true);
            mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            
            String configJson;
            if (config instanceof String) {
                // 如果已经是字符串，尝试解析再序列化，确保格式正确
                try {
                    Object parsed = mapper.readValue((String) config, Object.class);
                    configJson = mapper.writeValueAsString(parsed);
                } catch (Exception e) {
                    // 如果解析失败，直接使用原字符串
                    configJson = (String) config;
                }
            } else {
                // 对象转JSON字符串
                configJson = mapper.writeValueAsString(config);
            }
            
            // 更新协议配置
            Protocol updateProtocol = new Protocol();
            updateProtocol.setId(Long.valueOf(id));
            updateProtocol.setConfig(configJson);
            
            protocolDao.updateById(updateProtocol);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("配置信息序列化失败", e);
        }
    }
}