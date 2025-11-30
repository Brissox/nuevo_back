package cl.bakery.Pedidos.DTO;

import java.util.List;

import lombok.Data;

@Data
public class CrearPedidoDTO {

    private Long idUsuario;
    private String fecha;
    private Integer cantidad_productos;
    private String metodo_de_pago;
    private Integer descuentos;
    private List<DetallePedidoDTO> detalles;
}

