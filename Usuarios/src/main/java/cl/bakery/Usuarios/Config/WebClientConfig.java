package cl.bakery.Usuarios.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Bean(name = "descuentoWebClient")
    public WebClient descuentoWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8089")
                .defaultHeader("X-API-KEY", "123456789ABCDEF")
                .build();
    }
}
