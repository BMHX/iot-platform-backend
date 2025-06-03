package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.xym.convert.TenantAttributeDefinitionConvert;
import top.xym.dao.TenantAttributeDefinitionMapper;
import top.xym.dto.TenantAttributeDefinitionDTO;
import top.xym.entity.TenantAttributeDefinition;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.TenantAttributeDefinitionQuery;
import top.xym.service.ITenantAttributeDefinitionService;
import top.xym.vo.TenantAttributeDefinitionVO;

import java.util.List;

@Service
@AllArgsConstructor
public class TenantAttributeDefinitionServiceImpl extends ServiceImpl<TenantAttributeDefinitionMapper, TenantAttributeDefinition> implements ITenantAttributeDefinitionService {

    private final TenantAttributeDefinitionConvert tenantAttributeDefinitionConvert;

    @Override
    public boolean createTenantAttributeDefinition(TenantAttributeDefinitionDTO tenantAttributeDefinitionDTO) {
        TenantAttributeDefinition tenantAttributeDefinition = tenantAttributeDefinitionConvert.convertToEntity(tenantAttributeDefinitionDTO);
        return save(tenantAttributeDefinition);
    }

    @Override
    public boolean updateTenantAttributeDefinition(TenantAttributeDefinitionDTO tenantAttributeDefinitionDTO) {
        TenantAttributeDefinition tenantAttributeDefinition = tenantAttributeDefinitionConvert.convertToEntity(tenantAttributeDefinitionDTO);
        return updateById(tenantAttributeDefinition);
    }

    @Override
    public boolean deleteTenantAttributeDefinition(Integer id) {
        return removeById(id);
    }

    @Override
    public TenantAttributeDefinitionVO getTenantAttributeDefinitionById(Integer id) {
        TenantAttributeDefinition tenantAttributeDefinition = getById(id);
        return tenantAttributeDefinitionConvert.convertToVO(tenantAttributeDefinition);
    }

    @Override
    public PageResult<TenantAttributeDefinitionVO> page(TenantAttributeDefinitionQuery query) {
        Page<TenantAttributeDefinition> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<TenantAttributeDefinition> wrapper = Wrappers.lambdaQuery();
        // 构建查询条件，例如：
        // wrapper.eq(query.getTypeId() != null, TenantAttributeDefinition::getTypeId, query.getTypeId());
        // wrapper.like(query.getCode() != null, TenantAttributeDefinition::getCode, query.getCode());
        // wrapper.like(query.getName() != null, TenantAttributeDefinition::getName, query.getName());
        // wrapper.eq(query.getDataType() != null, TenantAttributeDefinition::getDataType, query.getDataType());
        // wrapper.eq(query.getStatus() != null, TenantAttributeDefinition::getStatus, query.getStatus());
        // wrapper.orderByDesc(TenantAttributeDefinition::getCreateTime);
        baseMapper.selectPage(page, wrapper);
        return new PageResult<>(tenantAttributeDefinitionConvert.convertToVOList(page.getRecords()), page.getTotal());
    }

    @Override
    public List<TenantAttributeDefinitionVO> list(TenantAttributeDefinitionQuery query) {
        LambdaQueryWrapper<TenantAttributeDefinition> wrapper = Wrappers.lambdaQuery();
        // 构建查询条件
        List<TenantAttributeDefinition> list = baseMapper.selectList(wrapper);
        return tenantAttributeDefinitionConvert.convertToVOList(list);
    }
}
