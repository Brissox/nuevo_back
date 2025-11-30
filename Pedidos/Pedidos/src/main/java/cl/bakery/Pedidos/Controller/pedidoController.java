package cl.bakery.Pedidos.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bakery.Pedidos.DTO.CrearPedidoDTO;
import cl.bakery.Pedidos.Model.pedido;
import cl.bakery.Pedidos.Services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class pedidoController {

    private final PedidoService pedidoService;

    public pedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
     public ResponseEntity<?> ListarPedidos(){
        List<pedido> pedidos = pedidoService.BuscarTodoPedido();
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok((pedidos));
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<pedido> crearPedido(@RequestBody CrearPedidoDTO dto) {
        return ResponseEntity.ok(pedidoService.crearPedido(dto));
    }

    @GetMapping("/uid/{uid}")
    public ResponseEntity<List<pedido>> BuscarporUsuario(@PathVariable String uid) {

        List<pedido> pedidos = pedidoService.BuscarPorUsuario(uid);

        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pedidos);
    }


}
    