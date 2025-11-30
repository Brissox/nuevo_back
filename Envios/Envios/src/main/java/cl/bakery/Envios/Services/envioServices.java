package cl.bakery.Envios.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bakery.Envios.Model.envio;
import cl.bakery.Envios.Repository.envioRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional

public class envioServices {

    @Autowired
    private envioRepository enviosrepository;

    public List<envio> BuscarTodoEnvios(){
        return enviosrepository.findAll();
    }
        public envio BuscarUnEnvio(Long ID_ENVIO){
        return enviosrepository.findById(ID_ENVIO).get();
    }

    public envio GuardarEnvios(envio envios){
        return enviosrepository.save(envios);

    }

    public void EliminarEnvio(Long ID_ENVIO){
        enviosrepository.deleteById(ID_ENVIO);

    }

}

/*  /*public List<envios> listarEnvios() {
        return enviosrepository.findAll().stream()
            .collect(Collectors.toList());
    }
    public List<envioUsuarioDTO> listarEnviosConUsuarios() {
        return enviosrepository.findAll().stream()
            .map(envioUsuarioDTO::new)
            .collect(Collectors.toList());
    }*/