package top.xym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.xym.convert.DeviceStatsConvert;
import top.xym.dao.DeviceStatsDAO;
import top.xym.dto.DeviceStatsDTO;
import top.xym.entity.DeviceStats;
import top.xym.query.DeviceStatsQuery;
import top.xym.repository.DeviceStatsRepository;
import top.xym.vo.DeviceStatsVO;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceStatsService {
    
    @Autowired
    private DeviceStatsRepository deviceStatsRepository;

    @Autowired
    private DeviceStatsDAO deviceStatsDAO;

    @Autowired
    private DeviceStatsConvert deviceStatsConvert;

    public List<DeviceStatsDTO> getAllDeviceStats() {
        return deviceStatsRepository.findAll().stream()
                .map(deviceStatsConvert::toDTO)
                .collect(Collectors.toList());
    }

    public DeviceStatsDTO getDeviceStatsById(Integer statusId) {
        return deviceStatsRepository.findById(statusId)
                .map(deviceStatsConvert::toDTO)
                .orElse(null);
    }

    public List<DeviceStatsVO> getDeviceStatsByDeviceId(Integer deviceId) {
        return deviceStatsDAO.findDeviceStatsWithDeviceInfo(deviceId).stream()
                .map(deviceStatsConvert::toVO)
                .collect(Collectors.toList());
    }

    public List<DeviceStatsVO> getLatestStatsForAllDevices() {
        return deviceStatsDAO.findLatestStatsForAllDevices().stream()
                .map(deviceStatsConvert::toVO)
                .collect(Collectors.toList());
    }

    public List<DeviceStatsVO> getStatsByTimeRange(DeviceStatsQuery query) {
        return deviceStatsDAO.findStatsByTimeRange(
                query.getDeviceId(),
                query.getStartTime().toString(),
                query.getEndTime().toString()
        ).stream()
                .map(deviceStatsConvert::toVO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DeviceStatsDTO createDeviceStats(DeviceStatsDTO deviceStatsDTO) {
        DeviceStats deviceStats = deviceStatsConvert.toEntity(deviceStatsDTO);
        DeviceStats savedStats = deviceStatsRepository.save(deviceStats);
        return deviceStatsConvert.toDTO(savedStats);
    }

    @Transactional
    public DeviceStatsDTO updateDeviceStats(Integer statusId, DeviceStatsDTO deviceStatsDTO) {
        return deviceStatsRepository.findById(statusId)
                .map(existingStats -> {
                    existingStats.setDeviceId(deviceStatsDTO.getDeviceId());
                    existingStats.setStatusData(deviceStatsDTO.getStatusData());
                    return deviceStatsConvert.toDTO(deviceStatsRepository.save(existingStats));
                })
                .orElse(null);
    }

    @Transactional
    public boolean deleteDeviceStats(Integer statusId) {
        return deviceStatsRepository.findById(statusId)
                .map(stats -> {
                    deviceStatsRepository.delete(stats);
                    return true;
                })
                .orElse(false);
    }
} 