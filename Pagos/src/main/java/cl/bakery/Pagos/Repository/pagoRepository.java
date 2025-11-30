package cl.bakery.Pagos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bakery.Pagos.Model.*;

public  interface pagoRepository extends JpaRepository<pago, Long> {
    
}
