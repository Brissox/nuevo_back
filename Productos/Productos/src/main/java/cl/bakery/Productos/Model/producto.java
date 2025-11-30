package cl.bakery.Productos.Model;

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
@Table(name="PRODUCTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="Todos los productos registrados en la empresa")

public class producto {

      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO")
    @Schema(description = "identificador del producto", example="1")
    private Long id_producto;

    @Column(name = "NOMBRE",nullable= false , length = 30)
    @Schema(description ="nombre del producto",example= "Monster")
    private String nombre;
    
    @Column(name = "DESCRIPCION",nullable= true , length = 255)
    @Schema(description ="detalle del producto ", example=" bebidas energéticas, conocida por su logotipo de garras verdes y su lema Unleash the Beast")
    private String descripcion;
    
    @Column(name = "CATEGORIA",nullable= true , length = 50)
    @Schema(description ="Categoría del producto (ejemplo: Bebidas, Alimentos, etc.)", example="coca cola company")
    private String categoria;

    @Column(name = "PRECIO",nullable= false , precision = 10)
    @Schema(description ="Valor del producto en moneda local", example="1600")
    private Long precio;

    @Column(name = "SKU",nullable= false , length = 12)
    @Schema(description ="Código de inventario ", example="123123a")
    private String sku;

    @Column(name = "ESTADO",nullable= false)
    @Schema(description ="estado en el que se encuentra el producto",example="A=Activo / I=Inactivo")
    private char estado;

@Column(name = "STOCK",nullable= false , length = 999)
    @Schema(description="Cantidad disponible en inventario",example="11222333")
    private int stock;

  @Column(name = "ENLACEIMG",nullable= false , length = 50)
    @Schema(description ="Enlace o URL de la imagen del producto", example="123123a")
    private String enlaceimg;


}
