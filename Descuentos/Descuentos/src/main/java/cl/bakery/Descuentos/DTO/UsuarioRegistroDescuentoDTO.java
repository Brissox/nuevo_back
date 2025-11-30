package cl.bakery.Descuentos.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UsuarioRegistroDescuentoDTO {
    private Long idUsuario;
    private String codigoRegistro;
    private String correo;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
}
