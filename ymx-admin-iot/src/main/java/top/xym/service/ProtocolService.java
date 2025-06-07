package top.xym.service;

import top.xym.entity.Protocol;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.ProtocolQuery;
import top.xym.vo.ProtocolVO;
import top.xym.dto.ProtocolDTO;
import java.util.List;

/**
 * 协议管理服务接口
 *
 * @author TraeAI
 */
public interface ProtocolService {

    /**
     * 分页查询协议列表
     *
     * @param query 查询条件
     * @return 分页结果
     */
    PageResult<ProtocolVO> getProtocolPage(ProtocolQuery query);

    /**
     * 查询所有协议列表
     *
     * @param query 查询条件
     * @return 协议列表
     */
    List<ProtocolVO> getProtocolList(ProtocolQuery query);

    /**
     * 根据ID获取协议信息
     *
     * @param id 协议ID
     * @return 协议信息
     */
    ProtocolVO getProtocolById(Integer id);

    /**
     * 创建协议
     *
     * @param protocolDTO 协议信息
     * @return 协议ID
     */
    Integer createProtocol(ProtocolDTO protocolDTO);

    /**
     * 更新协议
     *
     * @param id 协议ID
     * @param protocolDTO 协议信息
     */
    void updateProtocol(Integer id, ProtocolDTO protocolDTO);

    /**
     * 删除协议
     *
     * @param id 协议ID
     */
    void deleteProtocol(Integer id);

    /**
     * 批量删除协议
     *
     * @param ids 协议ID列表
     */
    void batchDeleteProtocol(List<Integer> ids);

    /**
     * 更新协议状态
     *
     * @param id 协议ID
     * @param status 状态
     */
    void updateProtocolStatus(Integer id, String status);

    /**
     * 保存协议配置
     *
     * @param id 协议ID
     * @param config 配置信息
     */
    void saveProtocolConfig(Integer id, Object config);
}
