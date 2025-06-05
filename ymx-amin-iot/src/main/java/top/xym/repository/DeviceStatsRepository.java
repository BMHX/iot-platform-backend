package top.xym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.xym.entity.DeviceStats;
import java.util.List;

@Repository
public interface DeviceStatsRepository extends JpaRepository<DeviceStats, Integer> {
    List<DeviceStats> findByDeviceId(Integer deviceId);
    List<DeviceStats> findByDeviceIdOrderByCreatedAtDesc(Integer deviceId);
} 