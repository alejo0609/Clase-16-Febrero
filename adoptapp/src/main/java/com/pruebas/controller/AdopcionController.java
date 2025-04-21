package com.pruebas.controller;

import com.pruebas.model.AdopcionModel;
import com.pruebas.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;




/**
 * Controlador para gestionar las adopciones.
 * Proporciona endpoints para realizar operaciones CRUD sobre las adopciones.
 * 
 * @author Alejandro Perez CC 8.029.742
 * @author Julian David Giraldo Murillo CC 1.007.240.094
 * @author Andres Escobar Vasquez CC 1.038.096.962
 * @author Jorge Andres Restrepo Cataño CC 98.648.720
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/adopcion")
public class AdopcionController {

    @Autowired
    private AdopcionService adopcionService;

    /**
     * Obtiene todas las adopciones registradas.
     * 
     * @return Lista de todas las adopciones.
     */



    @GetMapping
public ResponseEntity<?> getAllAdopcions() {
    try {
        return ResponseEntity.ok(adopcionService.findAll());
    } catch (Exception e) {
        e.printStackTrace(); // Muestra en consola el error exacto
        return ResponseEntity.status(500).body("Error al obtener adopciones: " + e.getMessage());
    }
}

    /**
     * Obtiene una adopción específica por su ID.
     * 
     * @param id Identificador de la adopción.
     * @return La adopción encontrada o un Optional vacío si no existe.
     */
    @GetMapping("/{id}")
    public Optional<AdopcionModel> getAdopcionById(@PathVariable Integer id) {
        return adopcionService.findById(id);
    }
    //Buscar el animal que este asociado a una adopcion
    @GetMapping("/{id}/animal")
        public ResponseEntity<?> obtenerAnimalPorAdopcion(@PathVariable Integer id) {
            Optional<AdopcionModel> adopcion = adopcionService.findById(id);
            if (adopcion.isPresent()) {
                return ResponseEntity.ok(adopcion.get().getAnimal());
            } else {
                return ResponseEntity.status(404).body("Adopción no encontrada");
            }
        }

    /**
    * Registra una nueva adopción y envía un correo de agradecimiento.
    * 
    * @param adopcion Datos de la adopción a registrar.
    * @return Respuesta indicando éxito o error.
    */
    @PostMapping
    public ResponseEntity<String> registrarAdopcion(@RequestBody AdopcionModel adopcion) {
        try {
            adopcionService.guardarAdopcion(adopcion);
            return ResponseEntity.ok("Formulario de adopción registrado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el formulario: " + e.getMessage());
        }
    }

    /**
     * Actualiza una adopción existente.
     * 
     * @param id Identificador de la adopción a actualizar.
     * @param adopcion Datos actualizados de la adopción.
     * @return La adopción actualizada.
     */
    @PutMapping("/{id}")
    public AdopcionModel updateAdopcion(@PathVariable Integer id, @RequestBody AdopcionModel adopcion) {
        adopcion.setIdAdopcion(id);
        return adopcionService.save(adopcion);
    }

    /**
     * Elimina una adopción por su ID.
     * 
     * @param id Identificador de la adopción a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteAdopcion(@PathVariable Integer id) {
        adopcionService.deleteById(id);
    }
}




