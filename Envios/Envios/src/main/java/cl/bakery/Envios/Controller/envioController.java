package cl.bakery.Envios.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bakery.Envios.Assembler.envioModelAssembler;
import cl.bakery.Envios.Model.envio;
import cl.bakery.Envios.Services.envioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/v1/Envios")
public class envioController {
    @Autowired
    private envioServices enviosservice;

    @Autowired
    private envioModelAssembler assembler;

    // ENDPOINT PARA BUSCAR TODOS LOS ENVIOS
    @GetMapping

    @Operation(summary = "ENVIOS", description = "Operacion que lista todos los envios")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente las ventas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = envio.class))), 
        @ApiResponse(responseCode = "404", description = "No se encontro ninguna venta", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))


    })

    public ResponseEntity<?> ListarTodo(){
        List<envio> envios = enviosservice.BuscarTodoEnvios();
        if (envios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran dato");
        } else {
            return ResponseEntity.ok(assembler.toCollectionModel(envios));
        }
    }

    // ENDPOINT PARA BUSCAR UN ENVIO
    @GetMapping("/{ID_ENVIO}")
    @Operation(summary = "ENVIO", description = "Operacion que lista un envio")
    @Parameters (value = {
        @Parameter (name="ID_ENVIO", description= "ID del envio que se buscara", in = ParameterIn.PATH, required= true)

    })
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el envio ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = envio.class))), 
        @ApiResponse(responseCode = "404", description = "No se encontro ningun envio", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })


    public ResponseEntity<?> BuscarEnvio(@PathVariable Long ID_ENVIO){

        try {
            envio enviosBuscado = enviosservice.BuscarUnEnvio(ID_ENVIO);
            return ResponseEntity.ok(assembler.toModel(enviosBuscado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran el envio");
        }
    }

// ENDPOINT PARA REGISTRAR UN ENVIO
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UN ENVIO", description = "ENDPOINT QUE REGISTRA UN ENVIO",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="ENVIO QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = envio.class))))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente el envio", content = @Content(mediaType = "application/json", schema = @Schema(implementation = envio.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el envio", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el envio")))
    })

    public ResponseEntity<?> GuardarEnvio(@RequestBody envio envioGuardar){
    try {
            envio enviosRegistrar = enviosservice.GuardarEnvios(envioGuardar);
            return ResponseEntity.ok(assembler.toModel(envioGuardar));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el envio");
    }
    }

    // ENDPOINT PARA EDITAR UN ENVIO
    @PutMapping("/{ID_ENVIO}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS

    @Operation(summary = "ENDPOINT QUE EDITA UN ENVIO", description = "ENDPOINT QUE EDITA UN ENVIO", requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description="ENVIO QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = envio.class))))
    @Parameters (value = {
        @Parameter (name="ID_ENVIO", description= "ID del envio que se editara", in = ParameterIn.PATH, required= true)})

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se edito correctamente el envio", content = @Content(mediaType = "application/json", schema = @Schema(implementation = envio.class))),
        @ApiResponse(responseCode = "500", description = "Envio no registrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el envio")))
    })
        
    public ResponseEntity<?> ActualizarEnvio(@PathVariable Long ID_ENVIO, @RequestBody envio enviosActualizar){
        try {
            envio enviosActualizado = enviosservice.BuscarUnEnvio(ID_ENVIO);
            enviosActualizado.setIdPedido(enviosActualizar.getIdPedido());
            enviosActualizado.setDireccionEnvio(enviosActualizar.getDireccionEnvio());
            enviosActualizado.setFechaEnvio(enviosActualizar.getFechaEnvio());
            enviosActualizado.setFechaEntrega(enviosActualizar.getFechaEntrega());
            enviosservice.GuardarEnvios(enviosActualizado);
            return ResponseEntity.ok(assembler.toModel(enviosActualizar));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El envio no esta registrado");
        }
    }
    

} /*
    @GetMapping
    public ResponseEntity<List<envioUsuarioDTO>> listar() {
        return ResponseEntity.ok(enviosservice.listarEnviosConUsuarios());
    }*/

