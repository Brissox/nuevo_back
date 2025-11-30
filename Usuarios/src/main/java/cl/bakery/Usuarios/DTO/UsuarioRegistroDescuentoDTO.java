package cl.bakery.Usuarios.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UsuarioRegistroDescuentoDTO {
    private Long idUsuario;
    private String codigoRegistro;
    private String correo;
    private LocalDate fechaNacimiento;
}
