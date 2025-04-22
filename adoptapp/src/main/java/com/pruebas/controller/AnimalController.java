 /** 
 * @author Alejandro Perez CC 8.029.742
 * @author Andres Escobar Vasquez CC 1.038.096.962
 **/

package com.pruebas.controller;

import com.pruebas.model.AnimalModel;
import com.pruebas.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/animal")

public class AnimalController {

@Autowired
private AnimalService animalService;

//  ----------    BUSQUEDA DE ANIMALES    ----------

// ✅ Obtener los últimos 10 animales registrados en el sistema
@GetMapping("/ultimos")
public List<AnimalModel> obtenerUltimos10Animales() {
return animalService.obtenerUltimos10Animales();
}

// ✅ Obtener los últimos 4 animales registrados en el sistema
@GetMapping("/ultimos4")
public List<AnimalModel> obtenerUltimos4Animales() {
return animalService.obtenerUltimos4Animales();
}


// ✅ Obtener los animales que se encuentren disponibles registrados en el sistema
@GetMapping("/disponibles")
public List<AnimalModel> obtenerAnimalesDisponibles() {
return animalService.obtenerAnimalesDisponibles();
}

// ✅ Obtener los animales que NO se encuentran disponibles registrados en el sistema
@GetMapping("/no_disponibles")
public List<AnimalModel> obtenerAnimalesNoDisponibles() {
return animalService.obtenerAnimalesNoDisponibles();
}

// ✅ Obtener todos los animales
@GetMapping
public List<AnimalModel> getAllAnimals() {
return animalService.findAll();
}

// ✅ Obtener un animal por ID
@GetMapping("/{id}")
public Optional<AnimalModel> getAnimalById(@PathVariable Integer id) {
return animalService.findById(id);
}

// Buscar por nombre
@GetMapping("/buscar/nombre/{nombre}")
public List<AnimalModel> buscarPorNombre(@PathVariable String nombre) {
    return animalService.buscarPorNombre(nombre);
}
// Buscar por raza
@GetMapping("/buscar/raza/{raza}")
public List<AnimalModel> buscarPorRaza(@PathVariable String raza) {
    return animalService.buscarPorRaza(raza);
}
// Buscar animales esterilizados
@GetMapping("/buscar/esterilizados")
public List<AnimalModel> buscarEsterilizados() {
    return animalService.buscarEsterilizados();
}
// Buscar animal por edad
@GetMapping("/buscar/edad/{edad}")
public List<AnimalModel> buscarPorEdad(@PathVariable String edad) {
    return animalService.buscarPorEdad(edad);
}

// - - - - - Buscar la tienda que esta asociado a un animal - - - - -
@GetMapping("/{id}/tienda")
    public ResponseEntity<?> obtenerTiendaPorAdopcion(@PathVariable Integer id) {
        Optional<AnimalModel> animal = animalService.findById(id);
        if (animal.isPresent()) {
            return ResponseEntity.ok(animal.get().getTienda());
        } else {
            return ResponseEntity.status(404).body("Tienda no encontrada");
        }
    }



// ✅ Crear un nuevo animal
@PostMapping
public AnimalModel createAnimal(@RequestBody AnimalModel animal) {
return animalService.save(animal);
}

// ✅ Crear una lista de animales
@PostMapping("/bulk")
public List<AnimalModel> createMultipleAnimals(@RequestBody List<AnimalModel> animals) {
return animalService.saveAll(animals);
}

// ✅ Actualizar un animal existente
@PutMapping("/{id}")
public AnimalModel updateAnimal(@PathVariable Integer id, @RequestBody AnimalModel animal) {
animal.setIdAnimal(id);
return animalService.save(animal);
}

// ✅ Eliminar un animal
@DeleteMapping("/{id}")
public void deleteAnimal(@PathVariable Integer id) {
animalService.deleteById(id);
}

}