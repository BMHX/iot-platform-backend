package top.xym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.xym.entity.Devices;

import java.util.List;
import java.util.Map;

/**
 * 设备表 DAO
 */
@Mapper
public interface DeviceDao extends BaseMapper<Devices> {

    /**
     * 统计在线设备数量
     * @return 在线设备数量
     */
    @Select("SELECT COUNT(*) FROM devices WHERE status = '0'")
    Integer countOnlineDevices();

    /**
     * 统计离线设备数量
     * @return 离线设备数量
     */
    @Select("SELECT COUNT(*) FROM devices WHERE status = '1'")
    Integer countOfflineDevices();

    /**
     * 统计故障设备数量
     * @return 故障设备数量
     */
    @Select("SELECT COUNT(*) FROM devices WHERE status = '2'")
    Integer countFaultDevices();

    /**
     * 统计维护中设备数量
     * @return 维护中设备数量
     */
    @Select("SELECT COUNT(*) FROM devices WHERE status = '3'")
    Integer countMaintenanceDevices();

    /**
     * 统计告警设备数量（模拟数据）
     * @return 告警设备数量
     */
    @Select("SELECT 0")
    Integer countAlertDevices();

    /**
     * 按城市获取设备分布（基于经纬度数据）
     * @return 设备分布数据
     */
    @Select("SELECT '北京' AS city, COUNT(*) AS count, '116.405285' AS longitude, '39.904989' AS latitude FROM devices WHERE longitude LIKE '116%' AND latitude LIKE '39%' " +
            "UNION ALL SELECT '上海' AS city, COUNT(*) AS count, '121.472644' AS longitude, '31.231706' AS latitude FROM devices WHERE longitude LIKE '121%' AND latitude LIKE '31%' " +
            "UNION ALL SELECT '广州' AS city, COUNT(*) AS count, '113.280637' AS longitude, '23.125178' AS latitude FROM devices WHERE longitude LIKE '113%' AND latitude LIKE '23%' " +
            "UNION ALL SELECT '深圳' AS city, COUNT(*) AS count, '114.085947' AS longitude, '22.547' AS latitude FROM devices WHERE longitude LIKE '114%' AND latitude LIKE '22%' " +
            "UNION ALL SELECT '杭州' AS city, COUNT(*) AS count, '120.153576' AS longitude, '30.287459' AS latitude FROM devices WHERE longitude LIKE '120%' AND latitude LIKE '30%' " +
            "UNION ALL SELECT '南京' AS city, COUNT(*) AS count, '118.767413' AS longitude, '32.041544' AS latitude FROM devices WHERE longitude LIKE '118%' AND latitude LIKE '32%' " +
            "UNION ALL SELECT '武汉' AS city, COUNT(*) AS count, '114.298572' AS longitude, '30.584355' AS latitude FROM devices WHERE longitude LIKE '114%' AND latitude LIKE '30%' " +
            "UNION ALL SELECT '成都' AS city, COUNT(*) AS count, '104.065735' AS longitude, '30.659462' AS latitude FROM devices WHERE longitude LIKE '104%' AND latitude LIKE '30%' " +
            "UNION ALL SELECT '西安' AS city, COUNT(*) AS count, '108.948024' AS longitude, '34.263161' AS latitude FROM devices WHERE longitude LIKE '108%' AND latitude LIKE '34%' " +
            "UNION ALL SELECT '重庆' AS city, COUNT(*) AS count, '106.504962' AS longitude, '29.533155' AS latitude FROM devices WHERE longitude LIKE '106%' AND latitude LIKE '29%'")
    List<Map<String, Object>> getDeviceGeoDistribution();

    /**
     * 获取设备增长趋势（按天）
     * @param days 天数
     * @return 设备增长趋势数据
     */
    @Select("WITH date_data AS (" +
            "  SELECT DATE_FORMAT(created_at, '%Y-%m-%d') as date " +
            "  FROM devices " +
            "  WHERE created_at IS NOT NULL " +
            "  GROUP BY DATE_FORMAT(created_at, '%Y-%m-%d')" +
            ") " +
            "SELECT dd.date, " +
            "  (SELECT COUNT(*) FROM devices WHERE DATE_FORMAT(created_at, '%Y-%m-%d') = dd.date) as daily_count, " +
            "  (SELECT COUNT(*) FROM devices WHERE created_at <= STR_TO_DATE(CONCAT(dd.date, ' 23:59:59'), '%Y-%m-%d %H:%i:%s')) as total_count " +
            "FROM date_data dd " +
            "ORDER BY dd.date DESC " +
            "LIMIT #{days}")
    List<Map<String, Object>> getDeviceGrowthTrend(@Param("days") int days);

    /**
     * 获取按设备类型的分布统计
     * @return 设备类型分布
     */
    @Select("SELECT device_type as type, COUNT(*) as count FROM devices WHERE device_type IS NOT NULL GROUP BY device_type")
    List<Map<String, Object>> getDeviceTypeDistribution();
}
