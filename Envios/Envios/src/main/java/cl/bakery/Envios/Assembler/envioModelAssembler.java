package cl.bakery.Envios.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import cl.bakery.Envios.Controller.envioController;
import cl.bakery.Envios.Model.envio;


@Component
public class envioModelAssembler implements RepresentationModelAssembler<envio, EntityModel<envio>>{

    @Override
    public EntityModel<envio> toModel(envio e) {
        return EntityModel.of(
            e,
            linkTo(methodOn(envioController.class).BuscarEnvio(e.getIdEnvio())).withRel("LINKS"),
            linkTo(methodOn(envioController.class).ListarTodo()).withRel("todas-los-envios"),
            linkTo(methodOn(envioController.class).ActualizarEnvio(e.getIdEnvio(), e)).withRel("actualiza-un-envio")
        );
    }

}