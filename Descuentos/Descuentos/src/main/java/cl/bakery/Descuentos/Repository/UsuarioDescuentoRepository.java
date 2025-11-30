package cl.bakery.Descuentos.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.bakery.Descuentos.Model.UsuarioDescuento;

@Repository
public interface UsuarioDescuentoRepository extends JpaRepository<UsuarioDescuento, Long> {

    boolean existsByIdUsuarioAndIdDescuento(Long idUsuario, Long idDescuento);

    java.util.List<UsuarioDescuento> findByIdUsuario(Long idUsuario);

    
}