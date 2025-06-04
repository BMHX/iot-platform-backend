package top.xym.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeviceProtocolVO {

    private Integer id;

    private Integer deviceId;

    private Integer protocolId;

    private LocalDateTime bindTime;
}
