package cl.bakery.Productos.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import cl.bakery.Productos.Controller.productoController;
import cl.bakery.Productos.Model.producto;

@Component

public class productoModelAssembler implements RepresentationModelAssembler<producto, EntityModel<producto>>{   
     @Override
    public EntityModel<producto> toModel(producto p){
        return EntityModel.of(
            p,
            linkTo(methodOn(productoController.class).BuscarProducto(p.getId_producto())).withRel("LINKS"),
            linkTo(methodOn(productoController.class).ListarProductos()).withRel("todas-los-productos"),
            linkTo(methodOn(productoController.class).ActualizarProducto(p.getId_producto(), p)).withRel("actualiza-una-venta")
        );
    }
}
    

