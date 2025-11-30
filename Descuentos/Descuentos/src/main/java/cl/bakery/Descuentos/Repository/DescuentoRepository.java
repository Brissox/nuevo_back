package cl.bakery.Descuentos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.bakery.Descuentos.Model.descuento;

@Repository
public interface DescuentoRepository extends JpaRepository<descuento, Long> {
    boolean existsByCodigo(String codigo);
    descuento findByCodigo(String codigo);
}
