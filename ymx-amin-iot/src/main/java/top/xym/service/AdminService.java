package top.xym.service;

import top.xym.dto.AdminDTO;
import top.xym.dto.AdminLoginDTO;
import top.xym.dto.AdminUpdateDTO;
import top.xym.query.AdminQuery;
import top.xym.vo.AdminInfoVO;
import top.xym.vo.AdminVO;
// import top.xym.framework.common.utils.PageResult; // If using PageResult for pagination
import java.util.List; // Or use PageResult

public interface AdminService {

    AdminInfoVO login(AdminLoginDTO loginDTO);

    boolean updateAdmin(AdminUpdateDTO updateDTO);

    AdminInfoVO getAdminById(Integer id); // Changed from int to Integer for consistency with Entity

    void saveAdmin(AdminDTO adminDTO);

    // For pagination, ymx-admin-iot uses PageResult.getList(). Adjust if your PageResult is different.
    // PageResult<AdminVO> page(AdminQuery query); // Option 1: Return PageResult
    List<AdminVO> page(AdminQuery query); // Option 2: Return List<AdminVO> as in your controller

    void deleteAdmin(Long id);

    List<AdminVO> getAllAdmins();

}