package cl.bakery.Pedidos.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductoDTO {

    @JsonProperty("id_producto") 
    private Long id;

    private String nombre;
    private String descripcion;
    private Integer stock;
    private Integer precio;

    @JsonProperty("enlaceimg")
    private String imagenUrl;
}
