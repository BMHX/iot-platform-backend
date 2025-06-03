package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.xym.convert.TenantAttributeValueConvert;
import top.xym.dao.TenantAttributeValueMapper;
import top.xym.dto.TenantAttributeValueDTO;
import top.xym.entity.TenantAttributeValue;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.TenantAttributeValueQuery;
import top.xym.service.ITenantAttributeValueService;
import top.xym.vo.TenantAttributeValueVO;

import java.util.List;

@Service
@AllArgsConstructor
public class TenantAttributeValueServiceImpl extends ServiceImpl<TenantAttributeValueMapper, TenantAttributeValue> implements ITenantAttributeValueService {

    private final TenantAttributeValueConvert tenantAttributeValueConvert;

    @Override
    public boolean createTenantAttributeValue(TenantAttributeValueDTO tenantAttributeValueDTO) {
        TenantAttributeValue tenantAttributeValue = tenantAttributeValueConvert.convertToEntity(tenantAttributeValueDTO);
        return save(tenantAttributeValue);
    }

    @Override
    public boolean updateTenantAttributeValue(TenantAttributeValueDTO tenantAttributeValueDTO) {
        TenantAttributeValue tenantAttributeValue = tenantAttributeValueConvert.convertToEntity(tenantAttributeValueDTO);
        return updateById(tenantAttributeValue);
    }

    @Override
    public boolean deleteTenantAttributeValue(Integer id) {
        return removeById(id);
    }

    @Override
    public TenantAttributeValueVO getTenantAttributeValueById(Integer id) {
        TenantAttributeValue tenantAttributeValue = getById(id);
        return tenantAttributeValueConvert.convertToVO(tenantAttributeValue);
    }

    @Override
    public PageResult<TenantAttributeValueVO> page(TenantAttributeValueQuery query) {
        Page<TenantAttributeValue> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<TenantAttributeValue> wrapper = Wrappers.lambdaQuery();
        // 构建查询条件，例如：
        // wrapper.eq(query.getTenantId() != null, TenantAttributeValue::getTenantId, query.getTenantId());
        // wrapper.eq(query.getAttrId() != null, TenantAttributeValue::getAttrId, query.getAttrId());
        // wrapper.orderByDesc(TenantAttributeValue::getCreateTime);
        baseMapper.selectPage(page, wrapper);
        return new PageResult<>(tenantAttributeValueConvert.convertToVOList(page.getRecords()), page.getTotal());
    }

    @Override
    public List<TenantAttributeValueVO> list(TenantAttributeValueQuery query) {
        LambdaQueryWrapper<TenantAttributeValue> wrapper = Wrappers.lambdaQuery();
        // 构建查询条件
        List<TenantAttributeValue> list = baseMapper.selectList(wrapper);
        return tenantAttributeValueConvert.convertToVOList(list);
    }
}