package cl.bakery.Pedidos.Controller;

import org.springframework.http.ResponseEntity;
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

    @PostMapping("/crear")
    public ResponseEntity<pedido> crearPedido(@RequestBody CrearPedidoDTO dto) {
        return ResponseEntity.ok(pedidoService.crearPedido(dto));
    }
}
    