package cl.bakery.Productos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfig {
       @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("Pasteleria Mil Sabores ")
            .version("2.0")
            .description("Documentación de la API de Productos")
        );
    }
    
}
