package top.xym.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 仪表盘数据 值对象
 *
 * @author YourName
 * @since YourDate
 */
@Data
public class DashboardDataVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备统计数据
     */
    private DeviceStatsVO stats;

    /**
     * 设备连接趋势数据
     */
    private TrendDataVO trend;

    /**
     * 设备统计数据
     */
    @Data
    public static class DeviceStatsVO {
        private Integer total;
        private Integer online;
        private Integer offline;
        private Integer alarm;
    }

    /**
     * 设备趋势数据
     */
    @Data
    public static class TrendDataVO {
        private List<String> dates;
        private List<Integer> online;
        private List<Integer> alarm;
    }
} 