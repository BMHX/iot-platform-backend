package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import top.xym.dto.TenantDTO;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result;
import top.xym.query.TenantQuery;
import top.xym.service.ITenantService;
import top.xym.vo.TenantVO;

import java.util.List;

@RestController
@RequestMapping("/api/tenant") // 建议使用 /api/tenants (复数形式)
@Tag(name = "租户管理")
@AllArgsConstructor
public class TenantController {

    private final ITenantService tenantService;

    @GetMapping
    @Operation(summary = "分页查询租户")
    public Result<PageResult<TenantVO>> page(@ParameterObject @Valid TenantQuery query) {
        PageResult<TenantVO> pageResult = tenantService.page(query);
        return Result.ok(pageResult);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取租户详情")
    public Result<TenantVO> get(@PathVariable("id") Integer id) {
        TenantVO vo = tenantService.get(id);
        return Result.ok(vo);
    }

    @PostMapping
    @Operation(summary = "新增租户")
    public Result<String> save(@Valid @RequestBody TenantDTO dto) {
        tenantService.save(dto);
        return Result.ok("租户添加成功");
    }

    @PutMapping("/{id}") // 保持与DTO中ID一致性，或直接使用dto.getId()
    @Operation(summary = "修改租户")
    public Result<String> update(@PathVariable("id") Integer id, @Valid @RequestBody TenantDTO dto) {
        dto.setId(id); // 确保DTO中的ID与路径变量一致
        tenantService.update(dto);
        return Result.ok("租户更新成功");
    }

    @DeleteMapping
    @Operation(summary = "删除租户")
    public Result<String> delete(@RequestBody List<Integer> ids) {
        tenantService.delete(ids);
        return Result.ok("租户删除成功");
    }
}