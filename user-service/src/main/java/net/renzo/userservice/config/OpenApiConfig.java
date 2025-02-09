package net.renzo.userservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                description = "User Service API",
                title = "User Service API",
                version = "v1"
        )
)
public class OpenApiConfig {
}
