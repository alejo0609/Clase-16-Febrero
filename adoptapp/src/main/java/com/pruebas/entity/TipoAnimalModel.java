
 
package com.pruebas.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tipo_animal")
@Data
public class TipoAnimalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int idTipoAnimal;

    @Column(nullable = false, length = 155)
    private String nombre_tipo_animal;
  
}
