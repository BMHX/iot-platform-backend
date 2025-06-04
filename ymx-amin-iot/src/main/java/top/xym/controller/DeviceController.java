package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result;
import top.xym.query.DeviceQuery;
import top.xym.service.DeviceService;
import top.xym.vo.DeviceVO;

import java.util.List;

@RestController
@RequestMapping("/api/device")
@Tag(name = "设备管理")
@AllArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping("/page")
    @Operation(summary = "分页查询设备列表")
    public Result<PageResult<DeviceVO>> page(DeviceQuery query) {
        return Result.ok(deviceService.page(query));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取设备信息")
    public Result<DeviceVO> get(@PathVariable("id") Integer id) {
        return Result.ok(deviceService.getById(id));
    }

    @PostMapping
    @Operation(summary = "新增设备")
    public Result<String> save(@RequestBody @Valid DeviceVO vo) {
        deviceService.save(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改设备")
    public Result<String> update(@RequestBody @Valid DeviceVO vo) {
        deviceService.update(vo);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除设备")
    public Result<String> delete(@PathVariable("id") Integer id) {
        deviceService.delete(id);
        return Result.ok();
    }

    @PutMapping("/integration/{id}/{integrationValue}")
    @Operation(summary = "根据ID更新设备集成状态")
    public Result<String> updateIntegration(@PathVariable("id") Integer id, @PathVariable("integrationValue") Integer integrationValue) {
        deviceService.updateDeviceIntegration(id, integrationValue);
        return Result.ok();
    }
    @GetMapping("/admin/{adminId}")
    @Operation(summary = "根据管理员ID查询设备列表")
    public Result<List<DeviceVO>> listByAdminId(@PathVariable("adminId") Integer adminId) {
        return Result.ok(deviceService.listByAdminId(adminId));
    }
}