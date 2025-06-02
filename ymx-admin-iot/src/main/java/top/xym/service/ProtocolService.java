package top.xym.service;

import top.xym.entity.Protocol;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.ProtocolQuery;
import top.xym.vo.ProtocolVO;
import top.xym.dto.ProtocolDTO;
import java.util.List;

/**
 * 协议表服务接口
 *
 * @author TraeAI
 */
public interface ProtocolService {

    /**
     * 分页查询协议列表
     *
     * @param query 查询参数
     * @return 分页结果
     */
    PageResult<ProtocolVO> getProtocolPage(ProtocolQuery query);

    /**
     * 获取所有协议列表 (根据查询条件)
     *
     * @param query 查询参数
     * @return 协议列表
     */
    List<ProtocolVO> getProtocolList(ProtocolQuery query);

    /**
     * 根据ID获取协议信息
     *
     * @param id 协议ID
     * @return 协议信息
     */
    ProtocolVO getProtocolById(Integer id); // 如果实体类ID是Long，这里改为Long
}
