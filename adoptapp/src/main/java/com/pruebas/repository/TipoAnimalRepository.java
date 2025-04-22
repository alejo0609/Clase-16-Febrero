 /** 
 * @author Alejandro Perez CC 8.029.742
 * @author Andres Escobar Vasquez CC 1.038.096.962
 **/
 
package com.pruebas.repository;


import com.pruebas.model.TipoAnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TipoAnimalRepository extends JpaRepository<TipoAnimalModel, Integer> {

}