package top.xym.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.xym.entity.TenantAttributeDefinition;
import top.xym.dto.TenantAttributeDefinitionDTO;
import top.xym.framework.common.utils.PageResult;
import top.xym.vo.TenantAttributeDefinitionVO;
import top.xym.query.TenantAttributeDefinitionQuery;

import java.util.List;

public interface ITenantAttributeDefinitionService extends IService<TenantAttributeDefinition> {

    /**
     * 创建租户扩展属性定义
     *
     * @param tenantAttributeDefinitionDTO 租户扩展属性定义DTO
     * @return 是否成功
     */
    boolean createTenantAttributeDefinition(TenantAttributeDefinitionDTO tenantAttributeDefinitionDTO);

    /**
     * 更新租户扩展属性定义
     *
     * @param tenantAttributeDefinitionDTO 租户扩展属性定义DTO
     * @return 是否成功
     */
    boolean updateTenantAttributeDefinition(TenantAttributeDefinitionDTO tenantAttributeDefinitionDTO);

    /**
     * 删除租户扩展属性定义
     *
     * @param id 属性定义ID
     * @return 是否成功
     */
    boolean deleteTenantAttributeDefinition(Integer id);

    /**
     * 获取租户扩展属性定义详情
     *
     * @param id 属性定义ID
     * @return 租户扩展属性定义VO
     */
    TenantAttributeDefinitionVO getTenantAttributeDefinitionById(Integer id);

    /**
     * 分页查询租户扩展属性定义
     *
     * @param query 查询参数
     * @return 分页结果
     */
    PageResult<TenantAttributeDefinitionVO> page(TenantAttributeDefinitionQuery query);

    /**
     * 根据条件查询租户扩展属性定义列表
     *
     * @param query 查询参数
     * @return 租户扩展属性定义列表
     */
    List<TenantAttributeDefinitionVO> list(TenantAttributeDefinitionQuery query);
}