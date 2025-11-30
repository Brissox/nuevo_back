package cl.bakery.Descuentos.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import cl.bakery.Descuentos.Controller.DescuentoController;
import cl.bakery.Descuentos.Model.descuento;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DescuentoModelAssembler implements RepresentationModelAssembler<descuento, EntityModel<descuento>> {

    @Override
    public EntityModel<descuento> toModel(descuento d) {

        return EntityModel.of(
            d,

            linkTo(methodOn(DescuentoController.class)
                    .buscarPorId(d.getId())).withSelfRel(),

            linkTo(DescuentoController.class).withRel("todos-los-descuentos"),

            linkTo(DescuentoController.class).slash(d.getId()).withRel("actualiza-descuento")
        );
    }
}
