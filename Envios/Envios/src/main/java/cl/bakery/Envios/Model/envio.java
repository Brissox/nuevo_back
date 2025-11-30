package cl.bakery.Envios.Model;

import java.time.LocalDateTime;

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
@Data
@Table(name="ENVIOS")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="Contiene la informacion relacionada con el proceso de envio de productos")

public class envio {
     @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENVIO")
    @Schema(description = "Identificador único del envío", example = "1")
    private Long idEnvio;

    @Column(name = "ID_PEDIDO", nullable = false)
    @Schema(description = "Identificador del pedido asociado al envío", example = "10")
    private Long idPedido;

    @Column(name = "DIRECCION_ENVIO", nullable = false, length = 150)
    @Schema(description = "Dirección completa donde se realizará el envío", example = "Av. Los Pinos 123, Santiago")
    private String direccionEnvio;

    @Column(name = "FECHA_ENVIO", nullable = false)
    @Schema(description = "Fecha y hora en que se despachó el pedido", example = "2025-11-04T14:30:00")
    private LocalDateTime fechaEnvio;

    @Column(name = "FECHA_ENTREGA")
    @Schema(description = "Fecha y hora estimada o real de entrega del pedido", example = "2025-11-06T09:00:00")
    private LocalDateTime fechaEntrega;
    
}
