package cl.bakery.Pedidos.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class itempedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    
    private Long U_ID;

    private Long ID_PRODUCTO;
    private String NOMBRE_PRODUCTO;
    private int CANTIDAD;
    private int PRECIO_UNITARIO;
    private int SUBTOTAL;

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO")
    @JsonBackReference
    private pedido pedido;
    
}