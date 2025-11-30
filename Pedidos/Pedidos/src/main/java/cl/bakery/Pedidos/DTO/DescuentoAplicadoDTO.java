package cl.bakery.Pedidos.DTO;

import lombok.Data;


@Data
public class DescuentoAplicadoDTO {
    private String codigo;
    private Integer porcentaje;
    private Double monto;
}
