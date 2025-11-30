package cl.bakery.Pedidos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bakery.Pedidos.Model.pedido;

public  interface pedidosRepository extends JpaRepository<pedido, Long> {
    
}
