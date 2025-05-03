
 
package com.pruebas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
@Data
public class RolesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRoles;

    @Column(nullable = false, length = 50)
    private String nombre;  // Ejemplo: "TIENDA", "ADMIN", etc.

    @Column(nullable = false)
    private LocalDateTime creado; 

    @Column(nullable = true)
    private LocalDateTime modificado; 

}