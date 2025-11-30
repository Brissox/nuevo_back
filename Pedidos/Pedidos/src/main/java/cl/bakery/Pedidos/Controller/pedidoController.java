package cl.bakery.Pedidos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bakery.Pedidos.Assembler.pedidoModelAssembler;
import cl.bakery.Pedidos.Model.pedido;
import cl.bakery.Pedidos.Services.pedidoServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/Pedidos")

public class pedidoController {
    
    
    @Autowired
    private pedidoServices pedidoservice;

    @Autowired
    private pedidoModelAssembler assembler;


    // ENDPOINT PARA BUSCAR TODOS LOS PEDIDOS
    @GetMapping

    @Operation(summary = "PEDIDOS", description = "Operacion que lista todos los pedidos")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los pedidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = pedido.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun pedido", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))


    })
    public ResponseEntity<?> ListarPedidos(){
        List<pedido> pedidos = pedidoservice.BuscarTodoPedido();
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el pedido");
            
        } else {
            return ResponseEntity.ok(assembler.toCollectionModel(pedidos));
        }
    }

     // ENDPOINT PARA BUSCAR UN PEDIDO
    @GetMapping("/{ID_PEDIDO}")

    @Operation(summary = "PEDIDO", description = "Operacion que lista un pedido")
    @Parameters (value = {
        @Parameter (name="ID_PEDIDO", description= "ID del pedido que se buscara", in = ParameterIn.PATH, required= true)

    })
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el pedido ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = pedido.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun pedido", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })

    public ResponseEntity<?> BuscarPedido(@PathVariable Long ID_PEDIDO){
        try {
            pedido pedidoBuscado = pedidoservice.BuscarUnPedido(ID_PEDIDO);
            return ResponseEntity.ok(assembler.toModel(pedidoBuscado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran el Pedido");

        }
    }

    // ENDPOINT PARA REGISTRAR UN PEDIDO
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UN PEDIDO", description = "ENDPOINT QUE REGISTRA UN PEDIDO",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="PEDIDO QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = pedido.class))))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente el pedido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = pedido.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el pedido", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el pedido")))
    })

    public ResponseEntity<?> GuardarPedido(@RequestBody pedido pedidoGuardar){
    try {
            pedido pedidoRegistrar = pedidoservice.GuardarPedido(pedidoGuardar);
            return ResponseEntity.ok(assembler.toModel(pedidoGuardar));
    } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el Pedido");
    }
    }

// ENDPOINT PARA EDITAR UN PEDIDO
    @PutMapping("/{ID_PEDIDO}")

    @Operation(summary = "ENDPOINT QUE EDITA UN PEDIDO", description = "ENDPOINT QUE EDITA UN PEDIDO", requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description="PEDIDO QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = pedido.class))))
    @Parameters (value = {
        @Parameter (name="ID_PEDIDO", description= "ID del pedido que se editara", in = ParameterIn.PATH, required= true)})

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se edito correctamente el pedido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = pedido.class))),
        @ApiResponse(responseCode = "500", description = "Pedido no esta registrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el pedido")))   
    })
    public ResponseEntity<?> ActualizarPedido(@PathVariable Long ID_PEDIDO, @RequestBody pedido pedidoActualizar){
        try {
            pedido pedidoActualizado = pedidoservice.BuscarUnPedido(ID_PEDIDO);
            pedidoActualizado.setFecha(pedidoActualizar.getFecha());
            pedidoActualizado.setVendedor(pedidoActualizar.getVendedor());
            pedidoActualizado.setMonto(pedidoActualizar.getMonto());
            pedidoActualizado.setCliente(pedidoActualizar.getCliente());
            pedidoservice.GuardarPedido(pedidoActualizado);
            return ResponseEntity.ok(assembler.toModel(pedidoActualizar));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no esta registrado");
        }
    }
    
/*
        @DeleteMapping("/{ID_PEDIDO}")
        public ResponseEntity<String> EliminarPedido(@PathVariable Long ID_PEDIDO){
            try {
                pedido pedidoBuscado = pedidoservice.BuscarUnPedido(ID_PEDIDO);
                pedidoservice.EliminarPedido(ID_PEDIDO);
                return ResponseEntity.status(HttpStatus.OK).body("Se elimina pedido");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no esta registrado");
            }
        }
 */

}
