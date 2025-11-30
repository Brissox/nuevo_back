package cl.bakery.Pedidos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.bakery.Pedidos.Model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar pedidos por usuario
    List<Pedido> findByIdUsuario(Long idUsuario);

    // Buscar por estado (PENDIENTE, PAGADO, CANCELADO)
    List<Pedido> findByEstado(String estado);
}
