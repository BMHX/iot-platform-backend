package top.xym.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.xym.entity.DeviceStats;
import java.util.List;
import java.util.Map;

@Repository
public class DeviceStatsDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findDeviceStatsWithDeviceInfo(Integer deviceId) {
        String sql = "SELECT ds.*, d.device_name " +
                    "FROM device_status ds " +
                    "LEFT JOIN device d ON ds.device_id = d.device_id " +
                    "WHERE ds.device_id = ? " +
                    "ORDER BY ds.created_at DESC";
        return jdbcTemplate.queryForList(sql, deviceId);
    }

    public List<Map<String, Object>> findLatestStatsForAllDevices() {
        String sql = "SELECT ds.*, d.device_name " +
                    "FROM device_status ds " +
                    "LEFT JOIN device d ON ds.device_id = d.device_id " +
                    "WHERE ds.created_at IN (SELECT MAX(created_at) FROM device_status GROUP BY device_id)";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> findStatsByTimeRange(Integer deviceId, String startTime, String endTime) {
        String sql = "SELECT ds.*, d.device_name " +
                    "FROM device_status ds " +
                    "LEFT JOIN device d ON ds.device_id = d.device_id " +
                    "WHERE ds.device_id = ? AND ds.created_at BETWEEN ? AND ? " +
                    "ORDER BY ds.created_at DESC";
        return jdbcTemplate.queryForList(sql, deviceId, startTime, endTime);
    }
} 