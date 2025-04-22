 /** 
 * @author Alejandro Perez CC 8.029.742
 * @author Andres Escobar Vasquez CC 1.038.096.962
 **/
 
package com.pruebas.repository;

import com.pruebas.model.TiendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiendaRepository extends JpaRepository<TiendaModel, Integer> {
}