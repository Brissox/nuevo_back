package cl.bakery.Pedidos.DTO;

import lombok.Data;

@Data
public class ItemCarritoDTO {
    private Long idProducto;
    private Integer cantidad;
    private Double precioUnitario;
}
