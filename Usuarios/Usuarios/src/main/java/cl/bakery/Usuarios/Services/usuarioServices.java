package cl.bakery.Usuarios.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  cl.bakery.Usuarios.Model.usuario;

import cl.bakery.Usuarios.Repository.usuarioRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional

public class usuarioServices {
    @Autowired

    private usuarioRepository usuariosrepository;

    public List<usuario> BuscarTodoUsuario(){
        return usuariosrepository.findAll();
    }

    public usuario BuscarUnUsuario(Long ID_USUARIO){
        return usuariosrepository.findById(ID_USUARIO).get();

    }

    public usuario GuardarUsuario(usuario usuario){
        return usuariosrepository.save(usuario);

    }

    public void EliminarUsuario(Long ID_USUARIO){
        usuariosrepository.deleteById(ID_USUARIO);
    }

}