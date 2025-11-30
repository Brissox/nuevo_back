package cl.bakery.Pedidos.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bakery.Pedidos.DTO.CrearPedidoDTO;
import cl.bakery.Pedidos.DTO.DetallePedidoDTO;
import cl.bakery.Pedidos.DTO.ProductoDTO;
import cl.bakery.Pedidos.Model.itempedido;
import cl.bakery.Pedidos.Model.pedido;
import cl.bakery.Pedidos.Repository.pedidosRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional



public class pedidoServices {

    

    @Autowired
    private pedidosRepository pedidorepository;
    
    public List<pedido> BuscarTodoPedido(){
        return pedidorepository.findAll();
    }

    public pedido BuscarUnPedido(Long ID_PEDIDO){
        return pedidorepository.findById(ID_PEDIDO).get();
    }

    public pedido GuardarPedido(pedido pedido){
        return pedidorepository.save(pedido);
    }

    public void EliminarPedido(Long ID_PEDIDO){
        pedidorepository.deleteById(ID_PEDIDO);
    }

    @Autowired
    private ProductoClientService productosClient;

     @Transactional
    public pedido crearPedido(CrearPedidoDTO dto) {

        // Crear el pedido base
        pedido pedido = new pedido();
        pedido.setUid(dto.getUid() != null ? dto.getUid().toString() : null);
        pedido.setCantidadProductos(dto.getCantidad_productos());
        pedido.setMetodoPago(dto.getMetodo_de_pago());
        pedido.setDescuentos(dto.getDescuentos());

        List<itempedido> items = new ArrayList<>();
        int total = 0;

        // Recorrer detalles
        for (DetallePedidoDTO det : dto.getDetalles()) {

            // Llamar al microservicio de productos
            ProductoDTO producto = productosClient.obtenerProducto(det.getIdProducto());

            if (producto.getStock() < det.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para " + producto.getNombre());

            }

            // Descontar stock en el microservicio de productos
            productosClient.descontarStock(det.getIdProducto(), det.getCantidad());

            // Calcular precio y subtotal
            int precioUnitario = producto.getPrecio();
            int subtotal = precioUnitario * det.getCantidad();
            total += subtotal;

            // Crear item del pedido
            itempedido item = new itempedido();
            item.setID_PRODUCTO(det.getIdProducto());
            item.setCANTIDAD(det.getCantidad());
            item.setNOMBRE_PRODUCTO(producto.getNombre());
            item.setPRECIO_UNITARIO(precioUnitario);
            item.setSUBTOTAL(subtotal);
            item.setPedido(pedido);

            items.add(item);
        }

        // Aplicar descuentos si vienen
        if (dto.getDescuentos() != null) {
            total -= dto.getDescuentos();
            if (total < 0)
                total = 0; // evitar totales negativos
        }

        // Asignar totales e ítems
        pedido.setTotal(total);
        pedido.setItems(items);

        // Guardar el pedido
        return pedidorepository.save(pedido);
    }

}

