package top.xym.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class DeviceProtocolDTO {

    private Integer id;

    @NotNull(message = "设备ID不能为空")
    private Integer deviceId;

    @NotNull(message = "协议ID不能为空")
    private Integer protocolId;

    private LocalDateTime bindTime;
}
