package top.xym.query;

import lombok.Data;
import top.xym.framework.common.query.Query;

@Data
public class DeviceQuery extends Query {
    private String deviceName;
    private String deviceCode;
    private Integer status;
    private String deviceType;
    private Integer adminId;
    private Integer deviceIntegration;
}