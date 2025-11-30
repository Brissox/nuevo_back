package cl.bakery.Usuarios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import  cl.bakery.Usuarios.Model.usuario;

public interface usuarioRepository extends JpaRepository<usuario, Long>{
    
}


