package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.xym.convert.PermissionConvert;
import top.xym.dao.PermissionDao;
import top.xym.entity.Permission;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.PermissionQuery;
import top.xym.service.PermissionService;
import top.xym.vo.PermissionVO;
import top.xym.dto.PermissionDTO;

import java.util.List;

/**
 * 权限表 服务实现
 *
 * @author YourName
 * @since YourDate
 */
@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionDao permissionDao;

    @Override
    public PageResult<PermissionVO> page(PermissionQuery query) {
        IPage<Permission> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(query.getName())) {
            wrapper.like(Permission::getName, query.getName());
        }
        wrapper.orderByDesc(Permission::getId); // 默认按ID降序
        IPage<Permission> resultPage = permissionDao.selectPage(page, wrapper);
        return new PageResult<>(PermissionConvert.INSTANCE.convertList(resultPage.getRecords()), resultPage.getTotal());
    }

    @Override
    public void save(PermissionDTO dto) {
        Permission entity = PermissionConvert.INSTANCE.convert(dto);
        permissionDao.insert(entity);
    }

    @Override
    public void update(PermissionDTO dto) {
        Permission entity = PermissionConvert.INSTANCE.convert(dto);
        permissionDao.updateById(entity);
    }

    @Override
    public void delete(Long id) {
        permissionDao.deleteById(id);
    }

    @Override
    public PermissionVO getById(Long id) {
        Permission entity = permissionDao.selectById(id);
        return PermissionConvert.INSTANCE.convert(entity);
    }

    @Override
    public List<PermissionVO> listAll() {
        List<Permission> list = permissionDao.selectList(null);
        return PermissionConvert.INSTANCE.convertList(list);
    }
}
