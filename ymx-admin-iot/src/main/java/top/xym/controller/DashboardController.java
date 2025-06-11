package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xym.framework.common.utils.Result;
import top.xym.service.DashboardService;

import java.util.List;
import java.util.Map;

/**
 * 数据大屏 Controller
 */
@RestController
@RequestMapping("/iot/dashboard")
@Tag(name = "数据大屏")
@AllArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/overview")
    @Operation(summary = "获取数据大屏概览数据")
    public Result<Map<String, Object>> getOverview() {
        Map<String, Object> data = dashboardService.getOverviewData();
        return Result.ok(data);
    }

    @GetMapping("/device-status")
    @Operation(summary = "获取设备状态分布")
    public Result<List<Map<String, Object>>> getDeviceStatus() {
        List<Map<String, Object>> data = dashboardService.getDeviceStatusDistribution();
        return Result.ok(data);
    }

    @GetMapping("/tenant-types")
    @Operation(summary = "获取租户类型分布")
    public Result<List<Map<String, Object>>> getTenantTypes() {
        List<Map<String, Object>> data = dashboardService.getTenantTypeDistribution();
        return Result.ok(data);
    }

    @GetMapping("/device-geo")
    @Operation(summary = "获取设备地理分布")
    public Result<Map<String, Object>> getDeviceGeo() {
        Map<String, Object> data = dashboardService.getDeviceGeoDistribution();
        return Result.ok(data);
    }

    @GetMapping("/device-growth")
    @Operation(summary = "获取设备增长趋势")
    public Result<Map<String, Object>> getDeviceGrowth() {
        Map<String, Object> data = dashboardService.getDeviceGrowthTrend();
        return Result.ok(data);
    }

    @GetMapping("/alert-levels")
    @Operation(summary = "获取告警级别分布")
    public Result<List<Map<String, Object>>> getAlertLevels() {
        List<Map<String, Object>> data = dashboardService.getAlertLevelDistribution();
        return Result.ok(data);
    }

    @GetMapping("/realtime-alerts")
    @Operation(summary = "获取实时告警数据")
    public Result<List<Map<String, Object>>> getRealtimeAlerts() {
        List<Map<String, Object>> data = dashboardService.getRealtimeAlerts();
        return Result.ok(data);
    }

    @GetMapping("/device-types")
    @Operation(summary = "获取设备类型分布")
    public Result<List<Map<String, Object>>> getDeviceTypes() {
        List<Map<String, Object>> data = dashboardService.getDeviceTypeDistribution();
        return Result.ok(data);
    }
} 