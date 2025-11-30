package cl.bakery.Descuentos.Model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_descuento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Tabla intermedia entre usuarios y descuentos")
public class UsuarioDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "id_descuento", nullable = false)
    private Long idDescuento;

    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;

}