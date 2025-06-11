package top.xym.service;

import java.util.List;
import java.util.Map;

/**
 * 数据大屏服务接口
 */
public interface DashboardService {

    /**
     * 获取数据大屏概览数据
     * 
     * @return 概览数据
     */
    Map<String, Object> getOverviewData();

    /**
     * 获取设备状态分布
     * 
     * @return 设备状态分布数据
     */
    List<Map<String, Object>> getDeviceStatusDistribution();

    /**
     * 获取租户类型分布
     * 
     * @return 租户类型分布数据
     */
    List<Map<String, Object>> getTenantTypeDistribution();

    /**
     * 获取设备地理分布
     * 
     * @return 设备地理分布数据
     */
    Map<String, Object> getDeviceGeoDistribution();

    /**
     * 获取设备增长趋势
     * 
     * @return 设备增长趋势数据
     */
    Map<String, Object> getDeviceGrowthTrend();

    /**
     * 获取告警级别分布
     * 
     * @return 告警级别分布数据
     */
    List<Map<String, Object>> getAlertLevelDistribution();

    /**
     * 获取实时告警数据
     * 
     * @return 实时告警数据
     */
    List<Map<String, Object>> getRealtimeAlerts();

    /**
     * 获取设备类型分布
     * 
     * @return 设备类型分布数据
     */
    List<Map<String, Object>> getDeviceTypeDistribution();
} 