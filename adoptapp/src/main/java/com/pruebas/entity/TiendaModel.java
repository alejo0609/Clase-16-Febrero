
 
package com.pruebas.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
@Table(name = "tienda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 100)
    @JsonProperty("correo_electronico")
    private String correoElectronico;

    @Column(nullable = true, length = 100)
    private String sitio_web;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String barrio;

    @Column(nullable = true)
    private Double latitud;

    @Column(nullable = true)
    private Double longitud;
    
    // // RELACION CON ANIMAL
    // @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL)
    // private List<AnimalModel> animales;

 
}



