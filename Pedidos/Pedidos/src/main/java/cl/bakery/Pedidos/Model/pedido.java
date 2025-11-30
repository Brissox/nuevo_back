package cl.bakery.Pedidos.Model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "PEDIDO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Todos los pedidos registrados en la empresa")
public class pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "U_ID", nullable = false)
    private String uid;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "CANTIDAD_PRODUCTOS", nullable = false)
    private Integer cantidadProductos;

    @Column(name = "METODO_PAGO", nullable = false)
    private String metodoPago;

    @Column(name = "DESCUENTOS")
    private Integer descuentos;

    @Column(name = "TOTAL")
    private Integer total;

    @Column(name = "ESTADO")
    private String estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<itempedido> items;
}
