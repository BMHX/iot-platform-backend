package top.xym.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result;
import top.xym.query.PermissionQuery;
import top.xym.service.PermissionService;
import top.xym.vo.PermissionVO;
import top.xym.dto.PermissionDTO;

import java.util.List;

/**
 * 权限表 Controller
 *
 * @author YourName
 * @since YourDate
 */
@RestController
@RequestMapping("/iot/permission")
@Tag(name = "权限管理")
@AllArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/page")
    @Operation(summary = "分页查询权限")
    public Result<PageResult<PermissionVO>> page(PermissionQuery query) {
        PageResult<PermissionVO> pageResult = permissionService.page(query);
        return Result.ok(pageResult);
    }

    @PostMapping
    @Operation(summary = "新增权限")
    public Result<?> save(@RequestBody @Valid PermissionDTO dto) {
        permissionService.save(dto);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改权限")
    public Result<?> update(@RequestBody @Valid PermissionDTO dto) {
        permissionService.update(dto);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除权限")
    public Result<?> delete(@PathVariable("id") Long id) {
        permissionService.delete(id);
        return Result.ok();
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询权限")
    public Result<PermissionVO> getById(@PathVariable("id") Long id) {
        PermissionVO vo = permissionService.getById(id);
        return Result.ok(vo);
    }

    @GetMapping("/list")
    @Operation(summary = "查询所有权限")
    public Result<List<PermissionVO>> listAll() {
        List<PermissionVO> list = permissionService.listAll();
        return Result.ok(list);
    }
}