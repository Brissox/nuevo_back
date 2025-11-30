package cl.bakery.Pedidos.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.bakery.Pedidos.DTO.ProductoDTO;

@Service
public class ProductoClientService {

    private final WebClient webClient;

    public ProductoClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Obtener información de un producto
    public ProductoDTO obtenerProducto(Long idProducto) {
        try {
            return webClient.get()
                    .uri("/Productos/" + idProducto)
                    .retrieve()
                    .bodyToMono(ProductoDTO.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar producto: " + idProducto);
        }
    }

    public void descontarStock(Long idProducto, int cantidad) {
        try {
            webClient.patch()
                    .uri(uriBuilder -> uriBuilder
                            .path("/Productos/descontar")
                            .queryParam("idProducto", idProducto)
                            .queryParam("cantidad", cantidad)
                            .build())
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Error al descontar stock del producto " + idProducto);
        }
    }
}
