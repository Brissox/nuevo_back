    package cl.bakery.Pedidos.Model;

    import com.fasterxml.jackson.annotation.JsonBackReference;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.Table;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;


    @Entity
    @Table(name = "ITEM_PEDIDO")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public class itempedido{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private Long id;

        @Column(name = "ID_PRODUCTO", nullable = false)
        private Long idProducto;

        @Column(name = "NOMBRE_PRODUCTO")
        private String nombreProducto;

        @Column(name = "CANTIDAD", nullable = false)
        private int cantidad;

        @Column(name = "PRECIO_UNITARIO", nullable = false)
        private int precioUnitario;

        @Column(name = "SUBTOTAL", nullable = false)
        private int subtotal;

        @ManyToOne
        @JoinColumn(name = "ID_PEDIDO", nullable = false)
        @JsonBackReference
        private pedido pedido;
    }