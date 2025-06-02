package top.xym.dto;

import lombok.Data;

@Data
public class DeviceDTO {
    private String deviceId;
    private String name;
    private Integer type;
    private Boolean status;
}