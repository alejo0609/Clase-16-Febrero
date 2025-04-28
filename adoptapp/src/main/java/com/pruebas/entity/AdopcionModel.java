 /** 
 * @author Alejandro Perez CC 8.029.742
 * @author Andres Escobar Vasquez CC 1.038.096.962
 **/
 
package com.pruebas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "adopcion")
@Data
public class AdopcionModel {

   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idadopcion") // 👈 agrega esto
    private Integer idAdopcion;


    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String ocupacion;

    @Column(name = "tipo_vivienda", nullable = false)
    private String tipoVivienda;

    @Column(name = "motivo_adopcion",nullable = false)
    private String motivoAdopcion;


    // @Column(name = "id_animal",nullable = false)
    // private Integer idAnimal; // Clave foránea (aunque aún no hay relación directa)

    @Column(nullable = false)
    private Boolean aprobado = false; // Inicialmente no aprobado

    // RELACIONES CON ANIMAL
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private AnimalModel animal;

    // Relacion con tienda; esta buena solo hace falta descomentar las linea de abajo
    // RELACIONES CON TIENDA
    // @ManyToOne
    // @JoinColumn(name = "tienda_id")
    // private TiendaModel tienda;

}
