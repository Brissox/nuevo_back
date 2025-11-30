package cl.bakery.Pedidos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.bakery.Pedidos.Model.itempedido;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<itempedido, Long> {

    // Traer detalles por pedido
    List<itempedido> findByPedidoId(Long id);
}