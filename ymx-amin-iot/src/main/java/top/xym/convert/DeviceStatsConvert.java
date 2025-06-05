package top.xym.convert;

import org.springframework.stereotype.Component;
import top.xym.dto.DeviceStatsDTO;
import top.xym.entity.DeviceStats;
import top.xym.vo.DeviceStatsVO;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class DeviceStatsConvert {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DeviceStatsDTO toDTO(DeviceStats entity) {
        if (entity == null) {
            return null;
        }
        DeviceStatsDTO dto = new DeviceStatsDTO();
        dto.setStatusId(entity.getStatusId());
        dto.setDeviceId(entity.getDeviceId());
        dto.setStatusData(entity.getStatusData());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public DeviceStats toEntity(DeviceStatsDTO dto) {
        if (dto == null) {
            return null;
        }
        DeviceStats entity = new DeviceStats();
        entity.setStatusId(dto.getStatusId());
        entity.setDeviceId(dto.getDeviceId());
        entity.setStatusData(dto.getStatusData());
        return entity;
    }

    public DeviceStatsVO toVO(DeviceStats entity, String deviceName) {
        if (entity == null) {
            return null;
        }
        DeviceStatsVO vo = new DeviceStatsVO();
        vo.setStatusId(entity.getStatusId());
        vo.setDeviceId(entity.getDeviceId());
        vo.setStatusData(entity.getStatusData());
        vo.setCreatedAt(entity.getCreatedAt());
        vo.setDeviceName(deviceName);
        vo.setFormattedTime(entity.getCreatedAt().format(formatter));
        return vo;
    }

    public DeviceStatsVO toVO(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        DeviceStatsVO vo = new DeviceStatsVO();
        vo.setStatusId((Integer) map.get("status_id"));
        vo.setDeviceId((Integer) map.get("device_id"));
        vo.setStatusData((String) map.get("status_data"));
        vo.setDeviceName((String) map.get("device_name"));
        vo.setCreatedAt(((java.sql.Timestamp) map.get("created_at")).toLocalDateTime());
        vo.setFormattedTime(vo.getCreatedAt().format(formatter));
        return vo;
    }
} 