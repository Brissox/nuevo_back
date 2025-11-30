package cl.bakery.Descuentos.DTO;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescuentoDTO {
    private Long idDescuento;
    private String codigo;
    private String descripcion;
    private BigDecimal porcentaje; 
}

