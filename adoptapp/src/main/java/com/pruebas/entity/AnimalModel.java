
 
package com.pruebas.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "animal")
@Data
public class AnimalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnimal;

    public void setIdAnimal(Integer id) {
        this.idAnimal = id;
    }

    @Column(nullable = false, length = 155)
    @JsonProperty("nombre_animal")
    private String nombreAnimal;

    @Column(nullable = false, length = 45)
    private String raza;

    @Column(nullable = false, length = 4)
    private String edad;

    @Column(nullable = false)
    private Boolean esterilizado;

   
    @Column(name = "estado_animal", nullable = false)
    private Boolean estadoAnimal;

    @Column(nullable = false, length = 255)
    @JsonProperty("imagen_animal")
    private String imagenAnimal;

    @Column(name = "correo_tienda")
    private String correoTienda;

// - - - - - RELACIONES - - - - -
    // Muchos animales pueden pertenecer a una tienda por ejemplo
    @ManyToOne
    @JoinColumn(name = "tienda_id") // clave foránea en la tabla "animal"
    private TiendaModel tienda;




}