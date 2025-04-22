 /** 
 * @author Alejandro Perez CC 8.029.742
 * @author Andres Escobar Vasquez CC 1.038.096.962
 **/
 
package com.pruebas.repository;

import com.pruebas.model.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalModel, Integer> {
    // Buscar los ultimos 10 animales disponibles
    List<AnimalModel> findTop10ByEstadoAnimalTrueOrderByIdAnimalDesc();
    // Buscar los ultimos 4 animales disponibles
    List<AnimalModel> findTop4ByEstadoAnimalTrueOrderByIdAnimalDesc();
    // Buscar animales disponibles
    List<AnimalModel> findByEstadoAnimalTrueOrderByIdAnimalDesc();
    // Buscar animales no disponibles
    List<AnimalModel> findByEstadoAnimalFalseOrderByIdAnimalDesc();
    // Buscar por nombre
    List<AnimalModel> findByNombreAnimal(String nombreAnimal);
    // Buscar por raza
    List<AnimalModel> findByRaza(String raza);
    // Buscar animales esterilizados
    List<AnimalModel> findByEsterilizadoTrue();
    // Buscar animales por la edad
    List<AnimalModel> findByEdad(String edad);
    // Relación con tienda
    long countByTienda_Id(Integer id);
}


