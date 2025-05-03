
 
package com.pruebas.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tipo_vacuna")
@Data
public class TipoVacunaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVacuna;

    @Column(nullable = false, length = 155)
    private String nombre_tipo_vacuna;
  
}