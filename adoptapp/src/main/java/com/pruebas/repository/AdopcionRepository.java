 /** 
 * @author Alejandro Perez CC 8.029.742
 * @author Julian David Giraldo Murillo CC 1.007.240.094
 * @author Andres Escobar Vasquez CC 1.038.096.962
 * @author Jorge Andres Restrepo Cataño CC 98.648.720
 **/
 
package com.pruebas.repository;


import com.pruebas.model.AdopcionModel;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AdopcionRepository extends JpaRepository<AdopcionModel, Integer> {
    long countByAnimal_IdAnimal(Integer idAnimal);
    
    

}



