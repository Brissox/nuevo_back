package cl.bakery.Productos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bakery.Productos.Model.producto;

public interface productoRepository  extends JpaRepository<producto, Long> {
    
}
