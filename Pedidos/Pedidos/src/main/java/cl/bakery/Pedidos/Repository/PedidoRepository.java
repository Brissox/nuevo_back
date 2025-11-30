package cl.bakery.Pedidos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.bakery.Pedidos.Model.pedido;


@Repository
public interface PedidoRepository extends JpaRepository<pedido, Long> {

    // Buscar por estado (PENDIENTE, PAGADO, CANCELADO)
    List<pedido> findByEstado(String estado);

    List<pedido> findByuid(String uid);

}
