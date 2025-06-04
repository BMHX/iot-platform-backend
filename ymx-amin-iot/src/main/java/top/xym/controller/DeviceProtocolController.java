package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xym.dto.DeviceProtocolDTO;
import top.xym.framework.common.utils.Result;
import top.xym.service.DeviceProtocolService;
import top.xym.vo.DeviceProtocolVO;

import java.util.List;

@RestController
@RequestMapping("/api/device-protocol")
@Tag(name = "设备协议管理")
@AllArgsConstructor
public class DeviceProtocolController {

    private final DeviceProtocolService deviceProtocolService;

    @PostMapping
    @Operation(summary = "新增设备协议绑定")
    public Result<String> save(@RequestBody @Valid DeviceProtocolDTO dto) {
        deviceProtocolService.save(dto);
        return Result.ok();
    }

    @PutMapping("/device/{id}/{deviceId}")
    @Operation(summary = "根据ID修改设备ID")
    public Result<String> updateDevice(@PathVariable("id") Integer id, @PathVariable("deviceId") Integer deviceId) {
        deviceProtocolService.updateDevice(id, deviceId);
        return Result.ok();
    }

    @PutMapping("/protocol/{id}/{protocolId}")
    @Operation(summary = "根据ID修改协议ID")
    public Result<String> updateProtocol(@PathVariable("id") Integer id, @PathVariable("protocolId") Integer protocolId) {
        deviceProtocolService.updateProtocol(id, protocolId);
        return Result.ok();
    }

    @DeleteMapping("/device/{deviceId}")
    @Operation(summary = "根据设备ID删除绑定")
    public Result<String> deleteByDeviceId(@PathVariable("deviceId") Integer deviceId) {
        deviceProtocolService.deleteByDeviceId(deviceId);
        return Result.ok();
    }

    @DeleteMapping("/protocol/{protocolId}")
    @Operation(summary = "根据协议ID删除绑定")
    public Result<String> deleteByProtocolId(@PathVariable("protocolId") Integer protocolId) {
        deviceProtocolService.deleteByProtocolId(protocolId);
        return Result.ok();
    }

    @GetMapping("/list")
    @Operation(summary = "查询所有设备协议绑定")
    public Result<List<DeviceProtocolVO>> listAll() {
        return Result.ok(deviceProtocolService.listAll());
    }

    @GetMapping("/list/device/{deviceId}")
    @Operation(summary = "根据设备ID查询绑定")
    public Result<List<DeviceProtocolVO>> listByDeviceId(@PathVariable("deviceId") Integer deviceId) {
        return Result.ok(deviceProtocolService.listByDeviceId(deviceId));
    }

    @GetMapping("/list/protocol/{protocolId}")
    @Operation(summary = "根据协议ID查询绑定")
    public Result<List<DeviceProtocolVO>> listByProtocolId(@PathVariable("protocolId") Integer protocolId) {
        return Result.ok(deviceProtocolService.listByProtocolId(protocolId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取设备协议绑定信息")
    public Result<DeviceProtocolVO> get(@PathVariable("id") Integer id) {
        return Result.ok(deviceProtocolService.getById(id));
    }
}