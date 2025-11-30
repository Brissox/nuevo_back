package cl.bakery.Pedidos.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import cl.bakery.Pedidos.Controller.pedidoController;
import cl.bakery.Pedidos.Model.pedido;

@Component
public class pedidoModelAssembler implements RepresentationModelAssembler<pedido, EntityModel<pedido>>{
      @Override
    public EntityModel<pedido> toModel(pedido p) {
        return EntityModel.of(
            p,
            linkTo(methodOn(pedidoController.class).BuscarPedido(p.getId_pedido())).withRel("LINKS"),
            linkTo(methodOn(pedidoController.class).ListarPedidos()).withRel("todos-los-pedidos"),
            linkTo(methodOn(pedidoController.class).ActualizarPedido(p.getId_pedido(), p)).withRel("actualiza-un-pedido")
        );
    }
    
}
