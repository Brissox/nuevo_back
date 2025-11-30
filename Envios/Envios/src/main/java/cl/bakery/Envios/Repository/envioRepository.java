package cl.bakery.Envios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bakery.Envios.Model.envio;

public interface envioRepository extends JpaRepository<envio, Long>{
    
}
