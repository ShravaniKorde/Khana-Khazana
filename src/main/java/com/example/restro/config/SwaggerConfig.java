package com.example.restro.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerSetup(){
        return new OpenAPI().info(
                new Info()
                        .title("Restaurant Mini Project")
                        .description("Sample swagger setup")
        );
    }
}
