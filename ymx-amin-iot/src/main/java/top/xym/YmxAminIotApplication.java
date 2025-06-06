package top.xym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"top.xym"})
public class YmxAminIotApplication {

    public static void main(String[] args) {
        SpringApplication.run(YmxAminIotApplication.class, args);
    }

}

