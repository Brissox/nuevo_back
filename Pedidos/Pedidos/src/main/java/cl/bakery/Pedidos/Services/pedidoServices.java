package cl.bakery.Pedidos.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
