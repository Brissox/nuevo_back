package cl.bakery.Pedidos.DTO;

import java.util.List;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long idUsuario;
    private Double total;
    private List<ItemPedidoDTO> items;
}
