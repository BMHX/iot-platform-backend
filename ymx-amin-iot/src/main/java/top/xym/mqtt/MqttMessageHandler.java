package top.xym.mqtt;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import top.xym.dto.DeviceStatsDTO;
import top.xym.service.DeviceStatsService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MqttMessageHandler {

    @Autowired
    private DeviceStatsService deviceStatsService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<?> message) {
        try {
            String topic = message.getHeaders().get("mqtt_receivedTopic", String.class);
            String payload = message.getPayload().toString();
            
            // 从主题中提取设备ID
            String deviceId = topic.split("/")[1];
            
            // 解析JSON数据
            JSONObject jsonData = JSON.parseObject(payload);
            
            // 创建设备状态DTO
            DeviceStatsDTO deviceStatsDTO = new DeviceStatsDTO();
            deviceStatsDTO.setDeviceId(Integer.parseInt(deviceId));
            deviceStatsDTO.setStatusData(jsonData.toJSONString());
            deviceStatsDTO.setCreatedAt(LocalDateTime.now());
            
            // 保存到数据库
            deviceStatsService.createDeviceStats(deviceStatsDTO);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 