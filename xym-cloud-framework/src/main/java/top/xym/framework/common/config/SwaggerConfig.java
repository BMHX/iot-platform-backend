package top.xym.framework.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 *
 * @author mqxu
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"top.xym"};
        return GroupedOpenApi.builder().group("XymCloud")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("1286280961@qq.com");

        return new OpenAPI().info(new Info()
                .title("xymCloud")
                .description("xymCloud")
                .contact(contact)
                .version("1.0")
                .termsOfService("https://xym.top")
                .license(new License().name("MIT")
                        .url("https://xym.top")));
    }

}