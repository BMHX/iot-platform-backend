package top.xym.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.xym.entity.TenantAttributeValue;
import top.xym.dto.TenantAttributeValueDTO;
import top.xym.framework.common.utils.PageResult;
import top.xym.vo.TenantAttributeValueVO;
import top.xym.query.TenantAttributeValueQuery;

import java.util.List;

public interface ITenantAttributeValueService extends IService<TenantAttributeValue> {

    /**
     * 创建租户扩展属性值
     *
     * @param tenantAttributeValueDTO 租户扩展属性值DTO
     * @return 是否成功
     */
    boolean createTenantAttributeValue(TenantAttributeValueDTO tenantAttributeValueDTO);

    /**
     * 更新租户扩展属性值
     *
     * @param tenantAttributeValueDTO 租户扩展属性值DTO
     * @return 是否成功
     */
    boolean updateTenantAttributeValue(TenantAttributeValueDTO tenantAttributeValueDTO);

    /**
     * 删除租户扩展属性值
     *
     * @param id 属性值ID
     * @return 是否成功
     */
    boolean deleteTenantAttributeValue(Integer id);

    /**
     * 获取租户扩展属性值详情
     *
     * @param id 属性值ID
     * @return 租户扩展属性值VO
     */
    TenantAttributeValueVO getTenantAttributeValueById(Integer id);

    /**
     * 分页查询租户扩展属性值
     *
     * @param query 查询参数
     * @return 分页结果
     */
    PageResult<TenantAttributeValueVO> page(TenantAttributeValueQuery query);

    /**
     * 根据条件查询租户扩展属性值列表
     *
     * @param query 查询参数
     * @return 租户扩展属性值列表
     */
    List<TenantAttributeValueVO> list(TenantAttributeValueQuery query);
}
