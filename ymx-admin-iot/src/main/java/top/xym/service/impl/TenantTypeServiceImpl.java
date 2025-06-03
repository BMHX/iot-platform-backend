package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.xym.convert.TenantTypeConvert;
import top.xym.dao.TenantTypeMapper;
import top.xym.dto.TenantTypeDTO;
import top.xym.entity.TenantType;
import top.xym.framework.common.exception.ServerException;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.TenantTypeQuery;
import top.xym.service.ITenantTypeService;
import top.xym.vo.TenantDetailVO;
import top.xym.vo.TenantTypeVO;

import java.util.List;

@Service
@AllArgsConstructor
public class TenantTypeServiceImpl extends ServiceImpl<TenantTypeMapper, TenantType> implements ITenantTypeService {

    @Override
    public PageResult<TenantTypeVO> page(TenantTypeQuery query) {
        IPage<TenantType> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<TenantType> wrapper = new LambdaQueryWrapper<>();

        if (StringUtils.hasText(query.getCode())) {
            wrapper.like(TenantType::getCode, query.getCode());
        }
        if (StringUtils.hasText(query.getName())) {
            wrapper.like(TenantType::getName, query.getName());
        }
        if (StringUtils.hasText(query.getStatus())) {
            wrapper.eq(TenantType::getStatus, query.getStatus());
        }
        wrapper.orderByDesc(TenantType::getCreateTime);

        IPage<TenantType> resultPage = baseMapper.selectPage(page, wrapper);
        List<TenantTypeVO> tenantTypeVOList = TenantTypeConvert.INSTANCE.convertList(resultPage.getRecords());
        return new PageResult<>(tenantTypeVOList, resultPage.getTotal());
    }

    @Override
    public TenantTypeVO get(Integer id) {
        TenantType entity = baseMapper.selectById(id);
        if (entity == null) {
            throw new ServerException("租户类型不存在");
        }
        return TenantTypeConvert.INSTANCE.convert(entity);
    }

    @Override
    public void save(TenantTypeDTO dto) {
        // 校验编码是否唯一
        TenantType existCode = baseMapper.selectOne(Wrappers.<TenantType>lambdaQuery().eq(TenantType::getCode, dto.getCode()));
        if (existCode != null) {
            throw new ServerException("租户类型编码已存在: " + dto.getCode());
        }
        TenantType entity = TenantTypeConvert.INSTANCE.convert(dto);
        baseMapper.insert(entity);
    }

    @Override
    public void update(TenantTypeDTO dto) {
        TenantType entity = baseMapper.selectById(dto.getId());
        if (entity == null) {
            throw new ServerException("租户类型不存在");
        }
        // 校验编码是否唯一（排除自身）
        TenantType existCode = baseMapper.selectOne(Wrappers.<TenantType>lambdaQuery()
                .eq(TenantType::getCode, dto.getCode())
                .ne(TenantType::getId, dto.getId()));
        if (existCode != null) {
            throw new ServerException("租户类型编码已存在: " + dto.getCode());
        }
        TenantTypeConvert.INSTANCE.update(dto, entity);
        baseMapper.updateById(entity);
    }

    @Override
    public void delete(List<Integer> ids) {
        // TODO: 检查是否有租户关联了这些类型，如果有关联则不允许删除或给出提示
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public List<TenantDetailVO> listTenantDetails() {
        List<TenantDetailVO> tenantDetails = baseMapper.listTenantDetails();
        return tenantDetails;
    }
}