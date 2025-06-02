package top.xym.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.xym.dto.AdminDTO;
import top.xym.entity.Admin;
import top.xym.query.AdminQuery;
import top.xym.vo.AdminVO;
import top.xym.framework.common.utils.PageResult;

public interface AdminService extends IService<Admin> {
    PageResult<AdminVO> page(AdminQuery query);
    AdminVO getAdminById(Long id);
    void saveAdmin(AdminDTO adminDto);
    void updateAdmin(AdminDTO adminDto);
    void deleteAdmin(Long id);
    /**
     * 为管理员设置权限套餐
     *
     * @param adminId 管理员ID
     * @param permissionId 权限套餐ID (prices表ID)
     */
    void assignPermission(Long adminId, Integer permissionId);

    /**
     * 修改管理员的权限套餐
     *
     * @param adminId 管理员ID
     * @param permissionId 新的权限套餐ID (prices表ID)
     */
    void updateAdminPermission(Long adminId, Integer permissionId);

    /**
     *移除管理员的权限套餐
     *
     * @param adminId 管理员ID
     */
    void removeAdminPermission(Long adminId);

    /**
     * 获取管理员的权限套餐信息
     *
     * @param adminId 管理员ID
     * @return PricesVO 权限套餐详情，如果未分配则返回null
     */
    top.xym.vo.PricesVO getAdminPermission(Long adminId);
}
