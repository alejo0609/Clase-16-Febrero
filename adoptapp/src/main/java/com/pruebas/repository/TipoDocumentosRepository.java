 /** 
 * @author Alejandro Perez R CC 8.029.742
 * @author Andres Escobar Vasquez CC 1.038.096.962
 **/
 
package com.pruebas.repository;


import com.pruebas.model.TipoDocumentosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TipoDocumentosRepository extends JpaRepository<TipoDocumentosModel, Integer> {

}
