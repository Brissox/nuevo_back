package cl.bakery.Pedidos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bakery.Pedidos.Model.pedido;

public  interface pedidosRepository extends JpaRepository<pedido, Long> {
    
    // Buscar pedidos por usuario
    List<pedido> findByuid(String uid);

    
}
