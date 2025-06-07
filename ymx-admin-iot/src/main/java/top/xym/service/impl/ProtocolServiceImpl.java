package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 协议表服务实现类
 *
 * @author TraeAI
 */
@Service
@AllArgsConstructor
public class ProtocolServiceImpl implements ProtocolService {

    private final ProtocolDao protocolDao;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
        // 默认排序，可以根据需要调整或移除
        wrapper.orderByDesc(Protocol::getId);
        return wrapper;
    }

    @Override
    public Integer createProtocol(ProtocolDTO protocolDTO) {
        // 模拟创建，实际项目中应该保存到数据库
        // 模拟返回ID
        return 100;
    }

    @Override
    public void updateProtocol(Integer id, ProtocolDTO protocolDTO) {
        // 模拟更新，实际项目中应该更新数据库
    }

    @Override
    public void deleteProtocol(Integer id) {
        // 模拟删除，实际项目中应该从数据库删除
    }

    @Override
    public void batchDeleteProtocol(List<Integer> ids) {
        // 模拟批量删除，实际项目中应该从数据库批量删除
    }

    @Override
    public void updateProtocolStatus(Integer id, String status) {
        // 模拟更新状态，实际项目中应该更新数据库
    }

    @Override
    public void saveProtocolConfig(Integer id, Object config) {
        // 模拟保存配置，实际项目中应该更新数据库
        try {
            // 将配置对象转换为JSON字符串
            String configJson = objectMapper.writeValueAsString(config);
            
            // 在实际项目中，这里应该将configJson保存到数据库中对应的协议记录的config字段
            
        } catch (JsonProcessingException e) {
            throw new RuntimeException("配置信息序列化失败", e);
        }
    }
    
    /**
     * 获取模拟协议列表数据
     */
    private List<ProtocolVO> getMockProtocolList() {
        List<ProtocolVO> list = new ArrayList<>();
        
        ProtocolVO p1 = new ProtocolVO();
        p1.setId(1);
        p1.setName("MQTT标准协议");
        p1.setType("MQTT");
        p1.setVersion("3.1.1");
        p1.setDescription("标准MQTT协议，用于设备数据上报");
        p1.setStatus("enabled");
        p1.setConfig("{\"server\":\"broker.example.com\",\"port\":1883,\"timeout\":10,\"keepAlive\":60,\"useTls\":false}");
        p1.setCreateTime(LocalDateTime.now().minusDays(30));
        p1.setUpdateTime(LocalDateTime.now().minusDays(15));
        p1.setCreator("管理员");
        list.add(p1);
        
        ProtocolVO p2 = new ProtocolVO();
        p2.setId(2);
        p2.setName("HTTP设备接入");
        p2.setType("HTTP");
        p2.setVersion("1.1");
        p2.setDescription("基于HTTP的设备接入协议");
        p2.setStatus("enabled");
        p2.setConfig("{\"server\":\"api.example.com\",\"port\":443,\"timeout\":5,\"useTls\":true}");
        p2.setCreateTime(LocalDateTime.now().minusDays(25));
        p2.setUpdateTime(LocalDateTime.now().minusDays(10));
        p2.setCreator("管理员");
        list.add(p2);
        
        ProtocolVO p3 = new ProtocolVO();
        p3.setId(3);
        p3.setName("CoAP轻量协议");
        p3.setType("CoAP");
        p3.setVersion("1.0");
        p3.setDescription("适用于资源受限设备的CoAP协议");
        p3.setStatus("enabled");
        p3.setConfig("{\"server\":\"coap.example.com\",\"port\":5683,\"timeout\":30}");
        p3.setCreateTime(LocalDateTime.now().minusDays(20));
        p3.setUpdateTime(LocalDateTime.now().minusDays(5));
        p3.setCreator("系统管理员");
        list.add(p3);
        
        ProtocolVO p4 = new ProtocolVO();
        p4.setId(4);
        p4.setName("LwM2M物联网协议");
        p4.setType("LwM2M");
        p4.setVersion("1.0");
        p4.setDescription("轻量级M2M设备管理协议");
        p4.setStatus("disabled");
        p4.setConfig("{\"server\":\"lwm2m.example.com\",\"port\":5684,\"timeout\":60,\"useTls\":true}");
        p4.setCreateTime(LocalDateTime.now().minusDays(15));
        p4.setUpdateTime(LocalDateTime.now().minusDays(2));
        p4.setCreator("系统管理员");
        list.add(p4);
        
        ProtocolVO p5 = new ProtocolVO();
        p5.setId(5);
        p5.setName("自定义二进制协议");
        p5.setType("CUSTOM");
        p5.setVersion("2.0");
        p5.setDescription("定制的二进制数据传输协议");
        p5.setStatus("enabled");
        p5.setConfig("{\"server\":\"custom.example.com\",\"port\":8080,\"timeout\":15}");
        p5.setCreateTime(LocalDateTime.now().minusDays(10));
        p5.setUpdateTime(LocalDateTime.now().minusDays(1));
        p5.setCreator("系统管理员");
        list.add(p5);
        
        return list;
    }
}