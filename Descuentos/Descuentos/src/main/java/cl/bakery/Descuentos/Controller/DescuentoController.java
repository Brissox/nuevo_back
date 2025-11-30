package cl.bakery.Descuentos.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bakery.Descuentos.DTO.DescuentoDTO;
import cl.bakery.Descuentos.DTO.UsuarioRegistroDescuentoDTO;
import cl.bakery.Descuentos.Model.descuento;
import cl.bakery.Descuentos.Services.DescuentoService;


@RestController
@RequestMapping("/descuentos")
public class DescuentoController {

    @Autowired
    private DescuentoService service;

    @GetMapping("/{id}")
    public ResponseEntity<descuento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/asignar")
    public ResponseEntity<?> asignarDescuentos(@RequestBody UsuarioRegistroDescuentoDTO dto) {
        service.asignarDescuentosPorReglas(dto);
        return ResponseEntity.ok("Descuentos asignados");
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<DescuentoDTO> obtenerDescuentosPorUsuario(@PathVariable Long idUsuario) {
        return service.obtenerDescuentosPorUsuario(idUsuario)
                .stream()
                .map(d -> new DescuentoDTO(d.getId(), d.getCodigo(), d.getDescripcion(), d.getPorcentaje()))
                .collect(Collectors.toList());
    }

}