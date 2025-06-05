package top.xym.query;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeviceStatsQuery {
    private Integer deviceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String sortField = "createdAt";
    private String sortOrder = "desc";
} 