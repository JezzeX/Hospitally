package com.group2.hospitally.config;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    public OpenAPI OpenApi(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Hospitally")
                                .description("Hospital Management System")
                                .version("1.0"));
    }
}
