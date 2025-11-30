package cl.bakery.Descuentos.Services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.bakery.Descuentos.DTO.UsuarioRegistroDescuentoDTO;
import cl.bakery.Descuentos.Model.UsuarioDescuento;
import cl.bakery.Descuentos.Model.descuento;
import cl.bakery.Descuentos.Repository.DescuentoRepository;
import cl.bakery.Descuentos.Repository.UsuarioDescuentoRepository;

@Service
public class DescuentoService {

    private final DescuentoRepository repository;
    private final UsuarioDescuentoRepository usuarioDescuentoRepository;

    public DescuentoService(DescuentoRepository repository, UsuarioDescuentoRepository usuarioDescuentoRepository) {
        this.repository = repository;
        this.usuarioDescuentoRepository = usuarioDescuentoRepository;
    }

    public List<descuento> listar() {
        return repository.findAll();
    }

    public descuento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Descuento no encontrado"));
    }

    public descuento crear(descuento d) {

        if (repository.existsByCodigo(d.getCodigo())) {
            throw new RuntimeException("El código ya existe");
        }

        return repository.save(d);
    }

    public descuento actualizar(Long id, descuento datos) {
        descuento d = buscarPorId(id);

        d.setCodigo(datos.getCodigo());
        d.setDescripcion(datos.getDescripcion());
        d.setPorcentaje(datos.getPorcentaje());

        return repository.save(d);
    }

    private void asignar(Long idUsuario, String codigoDescuento) {
        descuento d = repository.findByCodigo(codigoDescuento);
        if (d == null) {
            return;
        }
        UsuarioDescuento ud = new UsuarioDescuento();
        ud.setIdUsuario(idUsuario);
        ud.setIdDescuento(d.getId());
        ud.setFechaAsignacion(LocalDate.now());
        usuarioDescuentoRepository.save(ud);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public void asignarDescuentosPorReglas(UsuarioRegistroDescuentoDTO dto) {

        if (dto.getFechaNacimiento() != null) {
            int edad = Period.between(dto.getFechaNacimiento(), LocalDate.now()).getYears();
            if (edad > 50)
                asignar(dto.getIdUsuario(), "ADULTO_MAYOR");
        }

        if ("FELICES50".equals(dto.getCodigoRegistro()))
            asignar(dto.getIdUsuario(), "FELICES50");

        if (dto.getCorreo() != null && dto.getCorreo().endsWith("@duocuc.cl"))
            asignar(dto.getIdUsuario(), "DUOC_CUMPLE");
    }

    public List<descuento> obtenerDescuentosPorUsuario(Long idUsuario) {
        List<UsuarioDescuento> relaciones = usuarioDescuentoRepository.findByIdUsuario(idUsuario);
        return relaciones.stream()
                .map(rel -> repository.findById(rel.getIdDescuento())
                        .orElseThrow(() -> new RuntimeException("Descuento no encontrado")))
                .collect(Collectors.toList());
    }

}
