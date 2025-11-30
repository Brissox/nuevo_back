package cl.bakery.Usuarios.Model;

import java.sql.Date;

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
@Table(name="USUARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Todos los productos registrados en la empresa")

public class usuario {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_USUARIO")
    @Schema(description="identificador del usuario", example="1")
    private long id_usuario;

    @Column(name= "ID_ROL", nullable=false)
    @Schema(description="identificador del rol del usuario", example="1")
    private long id_rol;

    @Column(name= "NOMBRE",nullable= false , length = 30)
    @Schema(description="nombre del usuario",example="Juan")
    private String nombre;

    @Column(name= "APELLIDO_PATERNO",nullable= false , length = 30)
    @Schema(description="Apellido paterno del usuario", example="Perez")
    private String apellido_paterno;

    @Column(name= "APELLIDO_MATERNO",nullable= true , length = 30)
    @Schema(description="Apellido materno del usuario", example="Rojas")
    private String apellido_materno;

    @Column(name = "TELEFONO",nullable= true , length = 9)
    @Schema(description="Telefono de contacto del usuario", example="999777888")
    private Long telefono;

    @Column(name = "DIRECCION",nullable= true , length = 50)
    @Schema(description="Direccion del domicilio del usuario", example="inglaterra 123")
    private String direccion;

    @Column(name= "FECHA_NACIMIENTO",nullable= true)
    @Schema(description="Fecha de nacimiento", example="DD-MM-YYYY")
    private Date fecha_nacimiento;

    @Column(name = "CORREO",nullable= false , length = 100)
    @Schema(description="correo de contacto del usuario", example="xxxx@xxx.xxx")
    private String correo;

    @Column(name = "CONTRASENA",nullable= false , length = 20)
    @Schema(description="Contrasena del usuario", example="xxxxxxxxxxxx")
    private String contrasena;

    @Column(name = "RUN",nullable= false , length = 9)
    @Schema(description="rol unico natural del usuario sin digito verificador ni puntos",example="11222333")
    private int run;

    @Column(name = "DV",nullable= false , length = 1)
    @Schema(description="digito verificador del run", example="k")
    private String dv;

    @Column(name = "ESTADO",nullable= false , length = 1)
    @Schema(description="estado del usuario",example="A=Activo / I=Inactivo")
    private String estado;

    
}
