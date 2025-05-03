
package com.pruebas.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tipo_documentos")
@Data
public class TipoDocumentosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int idTipoDocumentos;

    @Column(nullable = false, length = 45)
    private String nombre_tipo_documento;
  
}
    