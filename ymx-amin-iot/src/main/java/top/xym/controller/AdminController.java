package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/amin")
@Tag(name = "管理员模块")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @Operation(summary = "管理员登录")
    @PostMapping("/login")
    public Result<AdminInfoVO> login(@RequestBody AdminLoginDTO loginDTO) {
        AdminInfoVO adminInfo = adminService.login(loginDTO);
        if (adminInfo == null) {
            return Result.error("登录失败，请检查用户名和密码");
        }
        return Result.ok(adminInfo);
    }

    @Operation(summary = "管理员注册")
    @PostMapping("/register")
    public Result<Void> register(@RequestBody AdminDTO adminDTO) {
        try {
            adminService.register(adminDTO);
            return Result.ok();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Operation(summary = "更新管理员信息")
    @PutMapping("/update")
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
        return Result.ok(list);
    }

    @GetMapping("/all")
    @Operation(summary = "获取所有管理员列表")
    public Result<List<AdminVO>> getAllAdmins() {
        List<AdminVO> list = adminService.getAllAdmins();
        return Result.ok(list);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除管理员")
    public Result<Void> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return Result.ok();
    }

    @Operation(summary = "获取管理员信息")
    @GetMapping("/profile/{id}")
    public Result<AdminDTO> getAdminProfile(@PathVariable Integer id) {
        AdminDTO adminDTO = adminService.getAdminProfile(id);
        if (adminDTO == null) {
            return Result.error("未找到管理员信息");
        }
        return Result.ok(adminDTO);
    }

    @Operation(summary = "更新管理员个人信息")
    @PutMapping("/profile")
    public Result<Void> updateAdminProfile(@RequestBody AdminDTO adminDTO) {
        try {
            adminService.updateAdminProfile(adminDTO);
            return Result.ok();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Operation(summary = "更新最后登录时间")
    @PutMapping("/last-login/{id}")
    public Result<Void> updateLastLoginTime(@PathVariable Integer id) {
        try {
            adminService.updateLastLoginTime(id);
            return Result.ok();
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

}