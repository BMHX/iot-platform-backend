package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xym.dto.TenantAttributeValueDTO;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result;
import top.xym.query.TenantAttributeValueQuery;
import top.xym.service.ITenantAttributeValueService;
import top.xym.vo.TenantAttributeValueVO;

import java.util.List;

@Tag(name = "租户扩展属性值管理")
@RestController
@RequestMapping("/iot/tenant-attribute-values")
@AllArgsConstructor
public class TenantAttributeValueController {

    private final ITenantAttributeValueService tenantAttributeValueService;

    @PostMapping
    @Operation(summary = "创建租户扩展属性值")
    public Result<Boolean> createTenantAttributeValue(@Valid @RequestBody TenantAttributeValueDTO tenantAttributeValueDTO) {
        return Result.ok(tenantAttributeValueService.createTenantAttributeValue(tenantAttributeValueDTO));
    }

    @PutMapping
    @Operation(summary = "更新租户扩展属性值")
    public Result<Boolean> updateTenantAttributeValue(@Valid @RequestBody TenantAttributeValueDTO tenantAttributeValueDTO) {
        return Result.ok(tenantAttributeValueService.updateTenantAttributeValue(tenantAttributeValueDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除租户扩展属性值")
    public Result<Boolean> deleteTenantAttributeValue(@PathVariable("id") Integer id) {
        return Result.ok(tenantAttributeValueService.deleteTenantAttributeValue(id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取租户扩展属性值详情")
    public Result<TenantAttributeValueVO> getTenantAttributeValueById(@PathVariable("id") Integer id) {
        return Result.ok(tenantAttributeValueService.getTenantAttributeValueById(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询租户扩展属性值")
    public Result<PageResult<TenantAttributeValueVO>> page(TenantAttributeValueQuery query) {
        return Result.ok(tenantAttributeValueService.page(query));
    }

    @GetMapping("/list")
    @Operation(summary = "查询租户扩展属性值列表")
    public Result<List<TenantAttributeValueVO>> list(TenantAttributeValueQuery query) {
        return Result.ok(tenantAttributeValueService.list(query));
    }
}