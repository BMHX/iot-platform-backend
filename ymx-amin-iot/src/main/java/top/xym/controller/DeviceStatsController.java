package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.xym.dto.DeviceStatsDTO;
import top.xym.query.DeviceStatsQuery;
import top.xym.service.DeviceStatsService;
import top.xym.vo.DeviceStatsVO;
import java.util.List;

@Tag(name = "设备状态管理", description = "设备状态数据的增删改查接口")
@RestController
@RequestMapping("/admin/api/device-stats")
public class DeviceStatsController {

    @Autowired
    private DeviceStatsService deviceStatsService;

    @Operation(summary = "获取所有设备状态", description = "获取系统中所有设备的状态记录")
    @GetMapping
    public ResponseEntity<List<DeviceStatsDTO>> getAllDeviceStats() {
        return ResponseEntity.ok(deviceStatsService.getAllDeviceStats());
    }

    @Operation(summary = "获取单个设备状态", description = "根据状态ID获取单个设备状态记录")
    @GetMapping("/{statusId}")
    public ResponseEntity<DeviceStatsDTO> getDeviceStatsById(@PathVariable Integer statusId) {
        DeviceStatsDTO stats = deviceStatsService.getDeviceStatsById(statusId);
        return stats != null ? ResponseEntity.ok(stats) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "获取设备状态历史", description = "获取指定设备的所有状态历史记录")
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<DeviceStatsVO>> getDeviceStatsByDeviceId(@PathVariable Integer deviceId) {
        return ResponseEntity.ok(deviceStatsService.getDeviceStatsByDeviceId(deviceId));
    }

    @Operation(summary = "获取最新设备状态", description = "获取所有设备的最新状态记录")
    @GetMapping("/latest")
    public ResponseEntity<List<DeviceStatsVO>> getLatestStatsForAllDevices() {
        return ResponseEntity.ok(deviceStatsService.getLatestStatsForAllDevices());
    }

    @Operation(summary = "搜索设备状态", description = "根据时间范围等条件搜索设备状态记录")
    @GetMapping("/search")
    public ResponseEntity<List<DeviceStatsVO>> searchDeviceStats(DeviceStatsQuery query) {
        return ResponseEntity.ok(deviceStatsService.getStatsByTimeRange(query));
    }

    @Operation(summary = "创建设备状态", description = "创建新的设备状态记录")
    @PostMapping
    public ResponseEntity<DeviceStatsDTO> createDeviceStats(@RequestBody DeviceStatsDTO deviceStatsDTO) {
        return ResponseEntity.ok(deviceStatsService.createDeviceStats(deviceStatsDTO));
    }

    @Operation(summary = "更新设备状态", description = "更新指定ID的设备状态记录")
    @PutMapping("/{statusId}")
    public ResponseEntity<DeviceStatsDTO> updateDeviceStats(
            @PathVariable Integer statusId,
            @RequestBody DeviceStatsDTO deviceStatsDTO) {
        DeviceStatsDTO updatedStats = deviceStatsService.updateDeviceStats(statusId, deviceStatsDTO);
        return updatedStats != null ? ResponseEntity.ok(updatedStats) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "删除设备状态", description = "删除指定ID的设备状态记录")
    @DeleteMapping("/{statusId}")
    public ResponseEntity<Void> deleteDeviceStats(@PathVariable Integer statusId) {
        boolean deleted = deviceStatsService.deleteDeviceStats(statusId);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
} 