package cl.bakery.Pedidos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "usuarioWebClient")
    public WebClient usuarioWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8084")
                .defaultHeader("X-API-KEY", "123456789ABCDEF")
                .build();
    }
    
}
