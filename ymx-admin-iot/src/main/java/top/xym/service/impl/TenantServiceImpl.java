package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.xym.convert.TenantConvert;
import top.xym.dao.TenantMapper;
import top.xym.dao.TenantTypeMapper; // 引入TenantTypeMapper用于校验typeId
import top.xym.dto.TenantDTO;
import top.xym.entity.Tenant;
import top.xym.entity.TenantType; // 引入TenantType用于校验typeId
import top.xym.framework.common.exception.ServerException;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.TenantQuery;
import top.xym.service.ITenantService;
import top.xym.vo.TenantVO;

import java.util.List;

@Service
@AllArgsConstructor
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

    private final TenantTypeMapper tenantTypeMapper; // 注入TenantTypeMapper

    @Override
    public PageResult<TenantVO> page(TenantQuery query) {
        IPage<Tenant> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<Tenant> wrapper = Wrappers.lambdaQuery();

        if (query.getTypeId() != null) {
            wrapper.eq(Tenant::getTypeId, query.getTypeId());
        }
        if (StringUtils.hasText(query.getName())) {
            wrapper.like(Tenant::getName, query.getName());
        }
        if (StringUtils.hasText(query.getContactPerson())) {
            wrapper.like(Tenant::getContactPerson, query.getContactPerson());
        }
        if (StringUtils.hasText(query.getContactPhone())) {
            wrapper.like(Tenant::getContactPhone, query.getContactPhone());
        }
        if (StringUtils.hasText(query.getStatus())) {
            wrapper.eq(Tenant::getStatus, query.getStatus());
        }
        if (StringUtils.hasText(query.getRegionCode())) {
            wrapper.eq(Tenant::getRegionCode, query.getRegionCode());
        }
        wrapper.orderByDesc(Tenant::getCreateTime);

        IPage<Tenant> resultPage = baseMapper.selectPage(page, wrapper);
        List<TenantVO> tenantVOList = TenantConvert.INSTANCE.convertList(resultPage.getRecords());
        return new PageResult<>(tenantVOList, resultPage.getTotal());
    }

    @Override
    public TenantVO get(Integer id) {
        Tenant entity = baseMapper.selectById(id);
        if (entity == null) {
            throw new ServerException("租户不存在");
        }
        return TenantConvert.INSTANCE.convert(entity);
    }

    private void validateTenantType(Integer typeId) {
        TenantType tenantType = tenantTypeMapper.selectById(typeId);
        if (tenantType == null) {
            throw new ServerException("指定的租户类型ID不存在: " + typeId);
        }
    }

    private void validateTenantName(String name, Integer id) {
        LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.<Tenant>lambdaQuery().eq(Tenant::getName, name);
        if (id != null) { // 更新时排除自身
            queryWrapper.ne(Tenant::getId, id);
        }
        Tenant existName = baseMapper.selectOne(queryWrapper);
        if (existName != null) {
            throw new ServerException("租户名称已存在: " + name);
        }
    }

    @Override
    public void save(TenantDTO dto) {
        validateTenantType(dto.getTypeId());
        validateTenantName(dto.getName(), null);

        Tenant entity = TenantConvert.INSTANCE.convert(dto);
        // 设置默认状态，如果DTO中没有提供
        if (!StringUtils.hasText(entity.getStatus())) {
            entity.setStatus("active");
        }
        baseMapper.insert(entity);
    }

    @Override
    public void update(TenantDTO dto) {
        Tenant entity = baseMapper.selectById(dto.getId());
        if (entity == null) {
            throw new ServerException("租户不存在");
        }
        validateTenantType(dto.getTypeId());
        validateTenantName(dto.getName(), dto.getId());

        TenantConvert.INSTANCE.update(dto, entity);
        baseMapper.updateById(entity);
    }

    @Override
    public void delete(List<Integer> ids) {
        // TODO: 考虑删除租户前的业务校验，例如是否有关联的设备、数据等
        // 例如：检查 tenant_attribute_values 表中是否还有与这些租户ID关联的记录
        // if (tenantAttributeValueMapper.selectCount(Wrappers.<TenantAttributeValue>lambdaQuery().in(TenantAttributeValue::getTenantId, ids)) > 0) {
        //     throw new ServerException("无法删除，所选租户下仍有关联的属性值");
        // }
        baseMapper.deleteBatchIds(ids);
    }
}