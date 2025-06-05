package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import top.xym.dto.AdminDTO;
import top.xym.query.AdminQuery;
import top.xym.service.AdminService;
import top.xym.vo.AdminVO;
import top.xym.framework.common.utils.Result;
import top.xym.vo.PricesVO;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "管理员模块")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 构造器注入
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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
        return Result.ok(adminService.page(query).getList());

    }

    @GetMapping("/{id}")
    @Operation(summary = "获取指定管理员")
    public Result<AdminVO> getAdmin(@PathVariable Long id) {
        return Result.ok(adminService.getAdminById(id));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理员信息")
    public Result<String> update(@Valid @RequestBody AdminDTO adminDto) {
        adminService.updateAdmin(adminDto);
        return Result.ok("管理员信息更新成功");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除管理员")
    public Result<String> delete(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return Result.ok("管理员删除成功");
    }

    @PostMapping("/{adminId}/permission/{permissionId}")
    @Operation(summary = "为管理员分配权限套餐")
    public Result<?> assignAdminPermission(
            @Parameter(description = "管理员ID") @PathVariable Long adminId,
            @Parameter(description = "权限套餐ID") @PathVariable Integer permissionId) {
        adminService.assignPermission(adminId, permissionId);
        return Result.ok();
    }

    @PutMapping("/{adminId}/permission/{permissionId}")
    @Operation(summary = "修改管理员的权限套餐")
    public Result<?> updateAdminPermission(
            @Parameter(description = "管理员ID") @PathVariable Long adminId,
            @Parameter(description = "新的权限套餐ID") @PathVariable Integer permissionId) {
        adminService.updateAdminPermission(adminId, permissionId);
        return Result.ok();
    }

    @DeleteMapping("/{adminId}/permission")
    @Operation(summary = "移除管理员的权限套餐")
    public Result<?> removeAdminPermission(
            @Parameter(description = "管理员ID") @PathVariable Long adminId) {
        adminService.removeAdminPermission(adminId);
        return Result.ok();
    }

    @GetMapping("/{adminId}/permission")
    @Operation(summary = "获取管理员的权限套餐信息")
    public Result<PricesVO> getAdminPermission(
            @Parameter(description = "管理员ID") @PathVariable Long adminId) {
        PricesVO pricesVO = adminService.getAdminPermission(adminId);
        return Result.ok(pricesVO);
    }
}
