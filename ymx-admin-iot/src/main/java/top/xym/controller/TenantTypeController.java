package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import top.xym.dto.TenantTypeDTO;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result;
import top.xym.query.TenantTypeQuery;
import top.xym.service.ITenantTypeService;
import top.xym.vo.TenantDetailVO;
import top.xym.vo.TenantTypeVO;

@RestController
@RequestMapping("/api/tenant-type")
@Tag(name = "租户类型管理")
@AllArgsConstructor
public class TenantTypeController {

    private final ITenantTypeService tenantTypeService;

    @GetMapping
    @Operation(summary = "分页查询租户类型")
    public Result<PageResult<TenantTypeVO>> page(@ParameterObject @Valid TenantTypeQuery query) {
        PageResult<TenantTypeVO> pageResult = tenantTypeService.page(query);
        return Result.ok(pageResult);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取租户类型详情")
    public Result<TenantTypeVO> get(@PathVariable("id") Integer id) {
        TenantTypeVO vo = tenantTypeService.get(id);
        return Result.ok(vo);
    }

    @PostMapping("/save")
    @Operation(summary = "新增租户类型")
    public Result<String> save(@Valid @RequestBody TenantTypeDTO dto) {
        tenantTypeService.save(dto);
        return Result.ok("租户类型添加成功");
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "修改租户类型")
    public Result<String> update(@Valid @RequestBody TenantTypeDTO dto) {
        tenantTypeService.update(dto);
        return Result.ok("租户类型更新成功");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除租户类型")
    public Result<String> delete(@RequestBody List<Integer> ids) {
        tenantTypeService.delete(ids);
        return Result.ok("租户类型删除成功");
    }
    @GetMapping("/details")
    @Operation(summary = "查询所有租户的详细信息列表")
    public Result<List<TenantDetailVO>> listTenantDetails() {
        return Result.ok(tenantTypeService.listTenantDetails());
    }
}