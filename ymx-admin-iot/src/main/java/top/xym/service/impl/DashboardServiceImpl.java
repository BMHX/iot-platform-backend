package top.xym.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.xym.dao.DeviceDao;
import top.xym.dao.TenantDao;
import top.xym.service.DashboardService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据大屏服务实现类
 */
@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DeviceDao deviceDao;
    private final TenantDao tenantDao;

    @Override
    public Map<String, Object> getOverviewData() {
        Map<String, Object> result = new HashMap<>();
        
        // 获取设备总数
        Long deviceTotal = deviceDao.selectCount(null);
        
        // 获取在线设备数量
        Integer onlineDeviceCount = deviceDao.countOnlineDevices();
        
        // 对于告警数量，由于没有对应表，使用模拟数据
        Integer alertCount = 356; // 模拟数据
        
        // 获取租户总数
        Long tenantCount = tenantDao.selectCount(null);
        
        result.put("deviceTotal", deviceTotal);
        result.put("onlineDeviceCount", onlineDeviceCount);
        result.put("alertCount", alertCount);
        result.put("tenantCount", tenantCount);
        
        return result;
    }

    @Override
    public List<Map<String, Object>> getDeviceStatusDistribution() {
        List<Map<String, Object>> result = new ArrayList<>();
        
        // 创建在线设备数据
        Map<String, Object> online = new HashMap<>();
        online.put("name", "在线");
        online.put("value", deviceDao.countOnlineDevices());
        online.put("itemStyle", Map.of("color", "#67C23A"));
        result.add(online);
        
        // 创建离线设备数据
        Map<String, Object> offline = new HashMap<>();
        offline.put("name", "离线");
        offline.put("value", deviceDao.countOfflineDevices());
        offline.put("itemStyle", Map.of("color", "#909399"));
        result.add(offline);
        
        // 创建故障设备数据
        Map<String, Object> fault = new HashMap<>();
        fault.put("name", "故障");
        fault.put("value", deviceDao.countFaultDevices());
        fault.put("itemStyle", Map.of("color", "#F56C6C"));
        result.add(fault);
        
        // 创建维护中设备数据
        Map<String, Object> maintenance = new HashMap<>();
        maintenance.put("name", "维护中");
        maintenance.put("value", deviceDao.countMaintenanceDevices());
        maintenance.put("itemStyle", Map.of("color", "#E6A23C"));
        result.add(maintenance);
        
        return result;
    }

    @Override
    public List<Map<String, Object>> getTenantTypeDistribution() {
        List<Map<String, Object>> result = new ArrayList<>();
        
        // 查询租户类型分布
        List<Map<String, Object>> tenantTypeData = tenantDao.getTenantTypeDistribution();
        
        // 设置颜色映射
        Map<String, String> colorMap = new HashMap<>();
        colorMap.put("学校", "#4ECA8A");
        colorMap.put("小区", "#409EFF");
        colorMap.put("驿站", "#E6A23C");
        colorMap.put("其他", "#909399");
        
        // 如果数据库中有数据，转换为前端需要的格式
        if (tenantTypeData != null && !tenantTypeData.isEmpty()) {
            for (Map<String, Object> item : tenantTypeData) {
                String name = String.valueOf(item.get("name"));
                Object value = item.get("value");
                
                Map<String, Object> typeItem = new HashMap<>();
                typeItem.put("name", name);
                typeItem.put("value", value);
                
                String color = colorMap.getOrDefault(name, "#909399");
                typeItem.put("itemStyle", Map.of("color", color));
                
                result.add(typeItem);
            }
        } else {
            // 无数据时使用模拟数据
            Map<String, Object> school = new HashMap<>();
            school.put("name", "学校");
            school.put("value", 42);
            school.put("itemStyle", Map.of("color", "#4ECA8A"));
            result.add(school);
            
            Map<String, Object> community = new HashMap<>();
            community.put("name", "小区");
            community.put("value", 28);
            community.put("itemStyle", Map.of("color", "#409EFF"));
            result.add(community);
            
            Map<String, Object> station = new HashMap<>();
            station.put("name", "驿站");
            station.put("value", 15);
            station.put("itemStyle", Map.of("color", "#E6A23C"));
            result.add(station);
            
            Map<String, Object> other = new HashMap<>();
            other.put("name", "其他");
            other.put("value", 4);
            other.put("itemStyle", Map.of("color", "#909399"));
            result.add(other);
        }
        
        return result;
    }

    @Override
    public Map<String, Object> getDeviceGeoDistribution() {
        Map<String, Object> result = new HashMap<>();
        
        // 查询设备地理分布
        List<Map<String, Object>> deviceGeoData = deviceDao.getDeviceGeoDistribution();
        
        // 处理查询结果为前端所需格式
        List<Map<String, Object>> devices = new ArrayList<>();
        if (deviceGeoData != null && !deviceGeoData.isEmpty()) {
            for (Map<String, Object> item : deviceGeoData) {
                String city = String.valueOf(item.get("city"));
                Object count = item.get("count");
                String longitude = String.valueOf(item.get("longitude"));
                String latitude = String.valueOf(item.get("latitude"));
                
                Map<String, Object> deviceItem = new HashMap<>();
                deviceItem.put("name", city);
                deviceItem.put("value", count);
                deviceItem.put("coordinate", new Double[]{
                    Double.parseDouble(longitude),
                    Double.parseDouble(latitude)
                });
                
                devices.add(deviceItem);
            }
        }
        
        // 如果数据为空，提供模拟数据
        if (devices.isEmpty()) {
            // 北京数据
            Map<String, Object> beijing = new HashMap<>();
            beijing.put("name", "北京");
            beijing.put("value", 156);
            beijing.put("coordinate", new Double[]{116.405285, 39.904989});
            devices.add(beijing);
            
            // 上海数据
            Map<String, Object> shanghai = new HashMap<>();
            shanghai.put("name", "上海");
            shanghai.put("value", 138);
            shanghai.put("coordinate", new Double[]{121.472644, 31.231706});
            devices.add(shanghai);
            
            // 广州数据
            Map<String, Object> guangzhou = new HashMap<>();
            guangzhou.put("name", "广州");
            guangzhou.put("value", 120);
            guangzhou.put("coordinate", new Double[]{113.280637, 23.125178});
            devices.add(guangzhou);
            
            // 深圳数据
            Map<String, Object> shenzhen = new HashMap<>();
            shenzhen.put("name", "深圳");
            shenzhen.put("value", 108);
            shenzhen.put("coordinate", new Double[]{114.085947, 22.547});
            devices.add(shenzhen);
            
            // 杭州数据
            Map<String, Object> hangzhou = new HashMap<>();
            hangzhou.put("name", "杭州");
            hangzhou.put("value", 95);
            hangzhou.put("coordinate", new Double[]{120.153576, 30.287459});
            devices.add(hangzhou);
        }
        
        result.put("devices", devices);
        result.put("center", new Double[]{104.5, 38.0});
        
        return result;
    }

    @Override
    public Map<String, Object> getDeviceGrowthTrend() {
        Map<String, Object> result = new HashMap<>();
        
        // 生成近30天的日期
        List<String> dates = new ArrayList<>();
        List<Integer> deviceGrowthData = new ArrayList<>();
        List<Integer> newDeviceData = new ArrayList<>();
        
        // 获取近30天的日期（从今天往前推30天）
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d");
        
        for (int i = 29; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            dates.add(date.format(formatter));
        }
        
        // 查询设备增长趋势数据
        List<Map<String, Object>> growthData = deviceDao.getDeviceGrowthTrend(30);
        
        // 从数据库查询的日期-数量映射
        Map<String, Integer> dateNewDeviceMap = new HashMap<>();
        Map<String, Integer> dateTotalDeviceMap = new HashMap<>();
        
        // 处理数据库返回的结果
        if (growthData != null && !growthData.isEmpty()) {
            for (Map<String, Object> entry : growthData) {
                String dateStr = String.valueOf(entry.get("date"));
                Integer dailyCount = Integer.valueOf(String.valueOf(entry.get("daily_count")));
                Integer totalCount = Integer.valueOf(String.valueOf(entry.get("total_count")));
                
                // 格式化日期为前端所需格式
                LocalDate entryDate = LocalDate.parse(dateStr);
                String formattedDate = entryDate.format(formatter);
                
                dateNewDeviceMap.put(formattedDate, dailyCount);
                dateTotalDeviceMap.put(formattedDate, totalCount);
            }
        }
        
        // 填充设备总量和新增设备数数组
        for (String date : dates) {
            Integer dailyCount = dateNewDeviceMap.getOrDefault(date, 0);
            Integer totalCount = dateTotalDeviceMap.getOrDefault(date, getLatestTotalCountBefore(date, dateTotalDeviceMap, dates));
            
            deviceGrowthData.add(totalCount);
            newDeviceData.add(dailyCount);
        }
        
        // 如果没有任何数据，使用总设备数作为基准
        if (deviceGrowthData.stream().allMatch(count -> count == 0)) {
            Long totalDevices = deviceDao.selectCount(null);
            deviceGrowthData = new ArrayList<>(Collections.nCopies(30, totalDevices.intValue()));
            // 最后一天显示所有设备为新增（仅用于演示）
            if (!newDeviceData.isEmpty()) {
                newDeviceData.set(newDeviceData.size() - 1, totalDevices.intValue());
            }
        }
        
        result.put("dates", dates);
        result.put("deviceGrowthData", deviceGrowthData);
        result.put("newDeviceData", newDeviceData);
        
        return result;
    }

    /**
     * 获取指定日期之前的最近一次设备总数
     * @param date 当前日期
     * @param dateTotalDeviceMap 日期-设备总数映射
     * @param allDates 所有日期列表（已排序）
     * @return 最近的设备总数，如果没有则返回0
     */
    private Integer getLatestTotalCountBefore(String targetDate, Map<String, Integer> dateTotalDeviceMap, List<String> allDates) {
        int targetIndex = allDates.indexOf(targetDate);
        if (targetIndex <= 0) return 0;
        
        // 从当前日期向前查找，找到第一个有数据的日期
        for (int i = targetIndex - 1; i >= 0; i--) {
            String previousDate = allDates.get(i);
            if (dateTotalDeviceMap.containsKey(previousDate)) {
                return dateTotalDeviceMap.get(previousDate);
            }
        }
        
        return 0;
    }

    @Override
    public List<Map<String, Object>> getAlertLevelDistribution() {
        // 由于数据库中没有相关表，直接返回模拟数据
        List<Map<String, Object>> result = new ArrayList<>();
        
        // 紧急告警
        Map<String, Object> emergency = new HashMap<>();
        emergency.put("name", "紧急");
        emergency.put("value", 45);
        emergency.put("itemStyle", Map.of("color", "#F56C6C"));
        result.add(emergency);
        
        // 重要告警
        Map<String, Object> important = new HashMap<>();
        important.put("name", "重要");
        important.put("value", 78);
        important.put("itemStyle", Map.of("color", "#E6A23C"));
        result.add(important);
        
        // 次要告警
        Map<String, Object> minor = new HashMap<>();
        minor.put("name", "次要");
        minor.put("value", 125);
        minor.put("itemStyle", Map.of("color", "#4ECA8A"));
        result.add(minor);
        
        // 提示告警
        Map<String, Object> info = new HashMap<>();
        info.put("name", "提示");
        info.put("value", 108);
        info.put("itemStyle", Map.of("color", "#909399"));
        result.add(info);
        
        return result;
    }

    @Override
    public List<Map<String, Object>> getRealtimeAlerts() {
        // 由于数据库中没有相关表，直接返回模拟数据
        List<Map<String, Object>> result = new ArrayList<>();
        
        // 离线告警
        Map<String, Object> offlineAlert = new HashMap<>();
        offlineAlert.put("time", "10:28:35");
        offlineAlert.put("content", "设备DEV20230005离线");
        offlineAlert.put("level", "warning");
        result.add(offlineAlert);
        
        // 温度告警
        Map<String, Object> temperatureAlert = new HashMap<>();
        temperatureAlert.put("time", "10:15:42");
        temperatureAlert.put("content", "设备DEV20230008温度超过阈值");
        temperatureAlert.put("level", "danger");
        result.add(temperatureAlert);
        
        // 连接异常告警
        Map<String, Object> connectionAlert = new HashMap<>();
        connectionAlert.put("time", "09:56:18");
        connectionAlert.put("content", "设备DEV20230012连接异常");
        connectionAlert.put("level", "warning");
        result.add(connectionAlert);
        
        // 数据异常告警
        Map<String, Object> dataAlert = new HashMap<>();
        dataAlert.put("time", "09:42:05");
        dataAlert.put("content", "设备DEV20230003数据异常");
        dataAlert.put("level", "info");
        result.add(dataAlert);
        
        // 电量低告警
        Map<String, Object> batteryAlert = new HashMap<>();
        batteryAlert.put("time", "09:30:56");
        batteryAlert.put("content", "设备DEV20230015电量低");
        batteryAlert.put("level", "warning");
        result.add(batteryAlert);
        
        return result;
    }

    @Override
    public List<Map<String, Object>> getDeviceTypeDistribution() {
        List<Map<String, Object>> result = new ArrayList<>();
        
        // 查询设备类型分布
        List<Map<String, Object>> deviceTypeData = deviceDao.getDeviceTypeDistribution();
        
        // 设置颜色映射 - 使用更鲜艳的颜色
        Map<String, String> colorMap = new HashMap<>();
        colorMap.put("温度传感器", "#FF6B6B"); // 红色
        colorMap.put("湿度传感器", "#4ECDC4"); // 青色
        colorMap.put("气压传感器", "#FFD166"); // 黄色
        colorMap.put("光照传感器", "#6A0572"); // 紫色
        colorMap.put("智能开关", "#1A535C"); // 深青色
        colorMap.put("摄像头", "#3A86FF"); // 蓝色
        colorMap.put("门禁", "#8338EC"); // 紫罗兰
        colorMap.put("烟感", "#FB5607"); // 橙色
        colorMap.put("其他", "#118AB2"); // 蓝绿色
        
        // 如果数据库中有数据，转换为前端需要的格式
        if (deviceTypeData != null && !deviceTypeData.isEmpty()) {
            for (Map<String, Object> item : deviceTypeData) {
                String type = String.valueOf(item.get("type"));
                Object count = item.get("count");
                
                Map<String, Object> typeItem = new HashMap<>();
                typeItem.put("name", type);
                typeItem.put("value", count);
                
                String color = colorMap.getOrDefault(type, getRandomBrightColor());
                typeItem.put("itemStyle", Map.of("color", color));
                
                result.add(typeItem);
            }
        } else {
            // 无数据时使用模拟数据，使用鲜艳的颜色
            Map<String, Object> temp = new HashMap<>();
            temp.put("name", "温度传感器");
            temp.put("value", 135);
            temp.put("itemStyle", Map.of("color", "#FF6B6B"));
            result.add(temp);
            
            Map<String, Object> humidity = new HashMap<>();
            humidity.put("name", "湿度传感器");
            humidity.put("value", 125);
            humidity.put("itemStyle", Map.of("color", "#4ECDC4"));
            result.add(humidity);
            
            Map<String, Object> pressure = new HashMap<>();
            pressure.put("name", "气压传感器");
            pressure.put("value", 78);
            pressure.put("itemStyle", Map.of("color", "#FFD166"));
            result.add(pressure);
            
            Map<String, Object> light = new HashMap<>();
            light.put("name", "光照传感器");
            light.put("value", 65);
            light.put("itemStyle", Map.of("color", "#6A0572"));
            result.add(light);
            
            Map<String, Object> other = new HashMap<>();
            other.put("name", "其他");
            other.put("value", 18);
            other.put("itemStyle", Map.of("color", "#118AB2"));
            result.add(other);
        }
        
        return result;
    }

    /**
     * 生成随机鲜艳颜色
     * @return 随机颜色十六进制字符串
     */
    private String getRandomBrightColor() {
        String[] brightColors = {
            "#FF6B6B", "#4ECDC4", "#FFD166", "#6A0572", "#1A535C", 
            "#3A86FF", "#8338EC", "#FB5607", "#118AB2", "#06D6A0",
            "#EF476F", "#FFC43D", "#1B9AAA", "#9C27B0", "#E91E63"
        };
        
        int randomIndex = (int)(Math.random() * brightColors.length);
        return brightColors[randomIndex];
    }
} 