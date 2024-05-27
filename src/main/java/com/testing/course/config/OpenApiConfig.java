package com.testing.course.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi(@Value("${server.servlet.context-path}") String contextPath) {
        return new OpenAPI()
                .info(new Info().title("Book store Api")
                        .description("Book store app ")
                        .version("v0.0.1"))
                .addServersItem(new Server().url(contextPath));
    }
}
