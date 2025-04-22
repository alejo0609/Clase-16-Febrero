 /** 
 * @author Alejandro Perez CC 8.029.742
 * @author Andres Escobar Vasquez CC 1.038.096.962
 **/
 
package com.pruebas.repository;

import com.pruebas.model.AdopcionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopcionRepository extends JpaRepository<AdopcionModel, Integer> {
    long countByAnimal_IdAnimal(Integer idAnimal);  
    // Relacion con tienda; esta buena solo hace falta descomentar la linea de abajo
    //long countByTienda_Id(Integer id);

}



