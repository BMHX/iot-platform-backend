package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xym.dto.TenantAttributeDefinitionDTO;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result;
import top.xym.query.TenantAttributeDefinitionQuery;
import top.xym.service.ITenantAttributeDefinitionService;
import top.xym.vo.TenantAttributeDefinitionVO;

import java.util.List;

@Tag(name = "租户扩展属性定义管理")
@RestController
@RequestMapping("/iot/tenant-attribute-definitions")
@AllArgsConstructor
public class TenantAttributeDefinitionController {

    private final ITenantAttributeDefinitionService tenantAttributeDefinitionService;

    @PostMapping
    @Operation(summary = "创建租户扩展属性定义")
    public Result<Boolean> createTenantAttributeDefinition(@Valid @RequestBody TenantAttributeDefinitionDTO tenantAttributeDefinitionDTO) {
        return Result.ok(tenantAttributeDefinitionService.createTenantAttributeDefinition(tenantAttributeDefinitionDTO));
    }

    @PutMapping
    @Operation(summary = "更新租户扩展属性定义")
    public Result<Boolean> updateTenantAttributeDefinition(@Valid @RequestBody TenantAttributeDefinitionDTO tenantAttributeDefinitionDTO) {
        return Result.ok(tenantAttributeDefinitionService.updateTenantAttributeDefinition(tenantAttributeDefinitionDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除租户扩展属性定义")
    public Result<Boolean> deleteTenantAttributeDefinition(@PathVariable("id") Integer id) {
        return Result.ok(tenantAttributeDefinitionService.deleteTenantAttributeDefinition(id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取租户扩展属性定义详情")
    public Result<TenantAttributeDefinitionVO> getTenantAttributeDefinitionById(@PathVariable("id") Integer id) {
        return Result.ok(tenantAttributeDefinitionService.getTenantAttributeDefinitionById(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询租户扩展属性定义")
    public Result<PageResult<TenantAttributeDefinitionVO>> page(TenantAttributeDefinitionQuery query) {
        return Result.ok(tenantAttributeDefinitionService.page(query));
    }

    @GetMapping("/list")
    @Operation(summary = "查询租户扩展属性定义列表")
    public Result<List<TenantAttributeDefinitionVO>> list(TenantAttributeDefinitionQuery query) {
        return Result.ok(tenantAttributeDefinitionService.list(query));
    }
}
