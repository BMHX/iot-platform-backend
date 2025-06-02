package top.xym.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * @author xym
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliyunSmsConfig {
    private String accessKey;
    private String accessKeySecret;
    private String signName;
    private String templateCode;
}