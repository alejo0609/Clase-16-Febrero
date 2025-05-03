
package com.pruebas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "historia_animal")
@Data
public class HistoriaAnimalModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistoriAanimal;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String estado_salud; 

    @Column(nullable = false)
    private Boolean chip;

    @Column(nullable = false)
    private LocalDateTime fecha_creado; 

    @Column(nullable = false)
    private LocalDateTime fecha_modificado; 

}