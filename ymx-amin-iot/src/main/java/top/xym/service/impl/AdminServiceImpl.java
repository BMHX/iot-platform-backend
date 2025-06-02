package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
// import org.springframework.security.crypto.password.PasswordEncoder; // For password encoding
import top.xym.convert.AdminConvert;
import top.xym.dao.AdminMapper;
import top.xym.dto.AdminDTO;
import top.xym.dto.AdminLoginDTO;
import top.xym.dto.AdminUpdateDTO;
import top.xym.entity.AdminEntity;
import top.xym.query.AdminQuery;
import top.xym.service.AdminService;
import top.xym.vo.AdminInfoVO;
import top.xym.vo.AdminVO;
// import top.xym.framework.common.exception.ServerException; // For custom exceptions
// import top.xym.framework.common.utils.PageResult; // If using PageResult

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    // private final PasswordEncoder passwordEncoder; // Inject if using Spring Security for password hashing

    @Override
    public AdminInfoVO login(AdminLoginDTO loginDTO) {
        AdminEntity admin = adminMapper.selectOne(new LambdaQueryWrapper<AdminEntity>()
                .eq(AdminEntity::getUsername, loginDTO.getUsername()));

        if (admin == null) {
            // throw new ServerException("用户名或密码错误");
            return null; // Or throw exception
        }

        // IMPORTANT: Implement password checking. Plain text comparison is insecure.
        // Example with PasswordEncoder:
        // if (!passwordEncoder.matches(loginDTO.getPassword(), admin.getPassword())) {
        // throw new ServerException("用户名或密码错误");
        // }
        if (!Objects.equals(loginDTO.getPassword(), admin.getPassword())) { // Placeholder: Replace with secure check
            // throw new ServerException("用户名或密码错误");
            return null; // Or throw exception
        }

        AdminInfoVO adminInfoVO = AdminConvert.INSTANCE.convertToInfoVO(admin);
        // adminInfoVO.setToken("generate_jwt_token_here"); // Generate and set token if applicable
        return adminInfoVO;
    }

    @Override
    public boolean updateAdmin(AdminUpdateDTO updateDTO) {
        AdminEntity existingAdmin = adminMapper.selectById(updateDTO.getId());
        if (existingAdmin == null) {
            // throw new ServerException("管理员不存在");
            return false;
        }

        AdminEntity adminToUpdate = AdminConvert.INSTANCE.convert(updateDTO);

        // Handle password update securely
        if (updateDTO.getNewPassword() != null && !updateDTO.getNewPassword().isEmpty()) {
            if (updateDTO.getCurrentPassword() == null || updateDTO.getCurrentPassword().isEmpty()) {
                // throw new ServerException("当前密码不能为空");
                return false; // Or throw
            }
            // IMPORTANT: Verify currentPassword against existingAdmin.getPassword()
            // if (!passwordEncoder.matches(updateDTO.getCurrentPassword(), existingAdmin.getPassword())) {
            // throw new ServerException("当前密码不正确");
            // }
            if (!Objects.equals(updateDTO.getCurrentPassword(), existingAdmin.getPassword())) { // Placeholder
                // throw new ServerException("当前密码不正确");
                return false;
            }
            // adminToUpdate.setPassword(passwordEncoder.encode(updateDTO.getNewPassword()));
            adminToUpdate.setPassword(updateDTO.getNewPassword()); // Placeholder: Hash the new password
        } else {
            adminToUpdate.setPassword(null); // Avoid updating password if not provided
        }

        // If username is not in DTO or is empty, don't update it (or handle as per requirements)
        if (updateDTO.getUsername() == null || updateDTO.getUsername().trim().isEmpty()) {
            adminToUpdate.setUsername(null); // Avoid updating username if not provided
        }

        return adminMapper.updateById(adminToUpdate) > 0;
    }

    @Override
    public AdminInfoVO getAdminById(Integer id) {
        AdminEntity admin = adminMapper.selectById(id);
        if (admin == null) {
            // throw new ServerException("管理员不存在");
            return null;
        }
        return AdminConvert.INSTANCE.convertToInfoVO(admin);
    }

    @Override
    public void saveAdmin(AdminDTO adminDTO) {
        AdminEntity adminEntity = AdminConvert.INSTANCE.convert(adminDTO);
        // IMPORTANT: Hash the password before saving
        // adminEntity.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        adminEntity.setCreatedTime(LocalDateTime.now());
        adminMapper.insert(adminEntity);
    }

    @Override
    public List<AdminVO> page(AdminQuery query) {
        // This matches your controller's expectation of List<AdminVO>
        // For proper pagination with total counts, use IPage and PageResult
        IPage<AdminEntity> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<AdminEntity> wrapper = new LambdaQueryWrapper<>();
        if (query.getUsername() != null && !query.getUsername().trim().isEmpty()) {
            wrapper.like(AdminEntity::getUsername, query.getUsername());
        }
        if (query.getIdentity() != null && !query.getIdentity().trim().isEmpty()) {
            wrapper.eq(AdminEntity::getIdentity, query.getIdentity());
        }
        // Add other query conditions from AdminQuery
        wrapper.orderByDesc(AdminEntity::getCreatedTime); // Example ordering

        adminMapper.selectPage(page, wrapper);
        return AdminConvert.INSTANCE.convertToVOList(page.getRecords());

        // If you were to return PageResult<AdminVO>:
        // IPage<AdminEntity> iPage = adminMapper.selectPage(new Page<>(query.getPage(), query.getLimit()), wrapper);
        // return new PageResult<>(AdminConvert.INSTANCE.convertToVOList(iPage.getRecords()), iPage.getTotal());
    }

    @Override
    public void deleteAdmin(Long id) {
        adminMapper.deleteById(id);
    }
}
