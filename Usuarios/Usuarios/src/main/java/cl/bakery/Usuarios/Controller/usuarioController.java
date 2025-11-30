package cl.bakery.Usuarios.Controller;

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

import cl.bakery.Usuarios.Assembler.usuarioModelAssembler;
import cl.bakery.Usuarios.Model.usuario;
import cl.bakery.Usuarios.Services.usuarioServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;




@RestController
@RequestMapping("/api/v1/Usuarios")
public class usuarioController {


@Autowired
    private usuarioServices usuarioservices;
@Autowired
    private usuarioModelAssembler assambler;
//ENDPOINT PARA listar todos los usuarios
    @GetMapping
    @Operation(summary = "ENDPOINT QUE LISTA TODOS LOS USUARIOS", description = "Operacion que lista todos los Usuarios")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los Usuarios", content = @Content(mediaType = "application/json", schema = @Schema(implementation = usuario.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun usuario", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))

    })
    public ResponseEntity<?> ListarUsuarios(){
        List<usuario> usuarios = usuarioservices.BuscarTodoUsuario();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos");
        } else {
            return ResponseEntity.ok(assambler.toCollectionModel(usuarios));
        }
    }




//ENDPOINT PARA listar un usuario por id
    @GetMapping("/{ID_USUARIO}")
    @Operation(summary = "ENDPOINT QUE LISTA UN USUARIO", description = "Operacion que lista un usuario")
    @Parameters (value = {
        @Parameter (name="ID_USUARIO", description= "ID del usuario que se buscara", in = ParameterIn.PATH, required= true)

    })
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el usuario ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = usuario.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun usuario", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })

    public ResponseEntity<?> BuscarUsuario(@PathVariable Long ID_USUARIO){

        try {
            usuario usuarioBuscado = usuarioservices.BuscarUnUsuario(ID_USUARIO);
            return ResponseEntity.ok(assambler.toModel(usuarioBuscado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra Usuario");
        }
        
    }


//ENDPOINT PARA agregar un usuario
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UN USUARIO", description = "ENDPOINT QUE REGISTRA UN USUARIO",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="USUARIO QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = usuario.class))))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente el usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = usuario.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el usuario", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el producto")))
    })

    public ResponseEntity<?> GuardarUsuario(@RequestBody usuario usuarioGuardar){
    try {
            usuario usuarioRegistrar = usuarioservices.GuardarUsuario(usuarioGuardar);
            return ResponseEntity.ok(assambler.toModel(usuarioRegistrar));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el Usuario");
    }
    }
    



//ENDPOINT PARA editar un usuario segun id
    @PutMapping("/{ID_USUARIO}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS
    
    @Operation(summary = "ENDPOINT QUE EDITA UN USUARIO", description = "ENDPOINT QUE EDITA UN USUARIO", requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description="USUARIO QUE SE VA A EDITAR", required = true, content = @Content(schema = @Schema(implementation = usuario.class))))
    @Parameters (value = {
        @Parameter (name="ID_USUARIO", description= "ID del usuario que se editara", in = ParameterIn.PATH, required= true)})

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se edito correctamente el usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = usuario.class))),
        @ApiResponse(responseCode = "500", description = "Usuario no esta registrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el usuario")))
    })


    public ResponseEntity<?> ActualizarUsuarios(@PathVariable Long ID_USUARIO, @RequestBody usuario usuarioActualizar){
        try {
            usuario usuarioActualizado = usuarioservices.BuscarUnUsuario(ID_USUARIO);
            usuarioActualizado.setNombre(usuarioActualizar.getNombre());
            usuarioActualizado.setId_rol(usuarioActualizar.getId_rol());
            usuarioActualizado.setApellido_paterno(usuarioActualizar.getApellido_paterno());
            usuarioActualizado.setApellido_materno(usuarioActualizar.getApellido_materno());
            usuarioActualizado.setFecha_nacimiento(usuarioActualizar.getFecha_nacimiento());
            usuarioActualizado.setCorreo(usuarioActualizar.getCorreo());
            usuarioActualizado.setDireccion(usuarioActualizar.getDireccion());
            usuarioActualizado.setTelefono(usuarioActualizar.getTelefono());
            usuarioActualizado.setContrasena(usuarioActualizar.getContrasena());
            usuarioActualizado.setRun(usuarioActualizar.getRun());
            usuarioActualizado.setDv(usuarioActualizar.getDv());
            usuarioActualizado.setEstado(usuarioActualizar.getEstado());
            usuarioservices.GuardarUsuario(usuarioActualizado);
            return ResponseEntity.ok(assambler.toModel(usuarioActualizado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no esta registrado");
        }
    }
    
    /*
        S@DeleteMapping("/{ID_USUARIO}")
        public ResponseEntity<String> EliminarUsuario(@PathVariable Long ID_USUARIO){
            try {
                usuario usuarioBuscado = usuarioservices.BuscarUnUsuario(ID_USUARIO);
                usuarioservices.EliminarUsuario(ID_USUARIO);
                return ResponseEntity.status(HttpStatus.OK).body("Se elimina Usuario");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no esta registrado");
            }
        }
            */

}
