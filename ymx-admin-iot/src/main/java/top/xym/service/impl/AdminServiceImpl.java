package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.xym.convert.AdminConvert;
import top.xym.convert.PricesConvert;
import top.xym.dao.AdminDao;
import top.xym.dao.PricesDao;
import top.xym.dto.AdminDTO;
import top.xym.entity.Admin;
import top.xym.entity.Prices;
import top.xym.query.AdminQuery;
import top.xym.service.AdminService;
import top.xym.vo.AdminVO;
import top.xym.framework.common.utils.PageResult;
import top.xym.vo.PricesVO;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Autowired
    private AdminConvert adminConvert;
    private final AdminDao adminDao;
    private final PricesDao pricesDao; // 注入 PricesDao
    public AdminServiceImpl(AdminDao adminDao, PricesDao pricesDao) {
        this.adminDao = adminDao;
        this.pricesDao = pricesDao;
    }

    @Override
    public PageResult<AdminVO> page(AdminQuery query) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(query.getUsername() != null, "username", query.getUsername());
        queryWrapper.eq(query.getIdentity() != null, "identity", query.getIdentity());
        List<Admin> list = list(queryWrapper);
        List<AdminVO> voList = adminConvert.convertList(list);
        return new PageResult<>(voList, voList.size());
    }

    @Override
    public AdminVO getAdminById(Long id) {
        Admin admin = getById(id);
        return adminConvert.convert(admin);
    }

    @Override
    public void saveAdmin(AdminDTO adminDto) {
        Admin admin = adminConvert.convert(adminDto);
        save(admin);
    }

    @Override
    public void updateAdmin(AdminDTO adminDto) {
        Admin admin = adminConvert.convert(adminDto);
        updateById(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        removeById(id);
    }



    @Override
    @Transactional
    public void assignPermission(Long adminId, Integer permissionId) {
        Admin admin = adminDao.selectById(adminId);
        if (admin == null) {
            throw new RuntimeException("管理员不存在"); // 或者自定义异常
        }
        if (permissionId != null) {
            Prices prices = pricesDao.selectById(permissionId);
            if (prices == null) {
                throw new RuntimeException("权限套餐不存在");
            }
        }
        admin.setPermissionId(permissionId);
        adminDao.updateById(admin);
    }

    @Override
    @Transactional
    public void updateAdminPermission(Long adminId, Integer permissionId) {
        // 与 assignPermission 逻辑类似，确保管理员和套餐存在
        assignPermission(adminId, permissionId);
    }

    @Override
    @Transactional
    public void removeAdminPermission(Long adminId) {
        Admin admin = adminDao.selectById(adminId);
        if (admin == null) {
            throw new RuntimeException("管理员不存在");
        }
        admin.setPermissionId(null); // 设置为null表示移除
        adminDao.updateById(admin);
    }

    @Override
    public PricesVO getAdminPermission(Long adminId) {
        Admin admin = adminDao.selectById(adminId);
        if (admin == null || admin.getPermissionId() == null) {
            return null;
        }
        Prices prices = pricesDao.selectById(admin.getPermissionId());
        return PricesConvert.INSTANCE.convert(prices);
    }
}