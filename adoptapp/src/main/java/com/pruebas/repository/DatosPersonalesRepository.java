 /** 
 * @author Alejandro Perez CC 8.029.742
 * @author Andres Escobar Vasquez CC 1.038.096.962
 **/
 
package com.pruebas.repository;


import com.pruebas.model.DatosPersonalesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatosPersonalesRepository extends JpaRepository<DatosPersonalesModel, Integer> {
     Optional<DatosPersonalesModel> findByEmail(String email);
     Optional<DatosPersonalesModel> findByDni(Long dni);
}