package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import top.xym.dto.AdminDTO;
import top.xym.dto.AdminLoginDTO;
import top.xym.dto.AdminUpdateDTO;
import top.xym.query.AdminQuery;
import top.xym.service.AdminService;
import top.xym.vo.AdminInfoVO;
import top.xym.vo.AdminVO;
import top.xym.framework.common.utils.Result;

import java.util.List;

@RestController
@RequestMapping("/api/amin") // Consistent with ymx-admin-iot
@Tag(name = "管理员模块")
@AllArgsConstructor // For constructor injection
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "管理员登录")
    @PostMapping("/login")
    public Result<AdminInfoVO> login(@Valid @RequestBody AdminLoginDTO loginDTO) {
        AdminInfoVO adminInfo = adminService.login(loginDTO);
        if (adminInfo == null) {
            return Result.error("用户名或密码错误");
        }
        return Result.ok(adminInfo);
    }

    @Operation(summary = "更新管理员信息")
    @PutMapping("/update") // Using PUT for updates
    public Result<String> updateAdmin(@Valid @RequestBody AdminUpdateDTO updateDTO) {
        boolean success = adminService.updateAdmin(updateDTO);
        if (success) {
            return Result.ok("管理员信息更新成功");
        }
        return Result.error("管理员不存在或更新失败");
    }

    @Operation(summary = "获取指定管理员信息")
    @GetMapping("/{id}")
    public Result<AdminInfoVO> getAdminById(@PathVariable Integer id) {
        AdminInfoVO adminInfo = adminService.getAdminById(id);
        if (adminInfo == null) {
            return Result.error("管理员不存在");
        }
        return Result.ok(adminInfo);
    }

    @PostMapping("/save")
    @Operation(summary = "新增管理员")
    public Result<String> save(@Valid @RequestBody AdminDTO adminDto) {
        adminService.saveAdmin(adminDto);
        return Result.ok("管理员添加成功");
    }

    @GetMapping
    @Operation(summary = "分页获取管理员列表")
    public Result<List<AdminVO>> page(@ParameterObject @Valid AdminQuery query) {
        List<AdminVO> list = adminService.page(query);
        if (list == null || list.isEmpty()){
            // Consider returning an empty list Result.ok(Collections.emptyList()) instead of error for no data found
            return Result.error("未查询到管理员信息");
        }
        return Result.ok(list);
    }

    @DeleteMapping("/delete/{id}") // Using DELETE for deletions
    @Operation(summary = "删除管理员")
    public Result<String> delete(@PathVariable Long id) { // PathVariable type should match service method parameter
        adminService.deleteAdmin(id);
        return Result.ok("管理员删除成功");
    }

}