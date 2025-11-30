package cl.bakery.Pedidos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.bakery.Pedidos.Model.ItemPedido;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<ItemPedido, Long> {

    // Traer detalles por pedido
    List<ItemPedido> findByPedidoId(Long idPedido);
}
