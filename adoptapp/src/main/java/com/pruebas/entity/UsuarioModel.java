
package com.pruebas.model;
import com.pruebas.model.DatosPersonalesModel;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class UsuarioModel {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idUsuario;

public void setIdUsuario(Integer id) {
    this.idUsuario = id;
}

// con estas líneas estaba funcionando bien el ingreso de un usuario previamente registrado
//  @Column(nullable = false, length = 155, unique = true)
//  private String email;

//  @Column(nullable = false, length = 155)
//  private String password;

@Column(nullable = false)
private Boolean estado_usuario;

// Codigo nuevo para iniciar la relacion con datos_personales

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "datos_personales_id", referencedColumnName = "idDatosPersonales")
private DatosPersonalesModel datosPersonales;



// Fin de relacion


}