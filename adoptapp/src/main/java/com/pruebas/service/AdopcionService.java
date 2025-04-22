/** 
 * @author Alejandro Perez CC 8.029.742
 * @author Andres Escobar Vasquez CC 1.038.096.962
 **/

package com.pruebas.service;

import com.pruebas.model.AdopcionModel;
import com.pruebas.model.AnimalModel; // Por la RELACION que tienen
//import com.pruebas.model.TiendaModel; // Por la RELACION que tienen

import com.pruebas.repository.AdopcionRepository; 
import com.pruebas.repository.AnimalRepository; // Por la RELACION que tienen
//import com.pruebas.repository.TiendaRepository; // Por la RELACION que tienen

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class AdopcionService {

// - - - - - Inyeccion de las clases que se usan de manera externa - - - - -
    @Autowired
    private AdopcionRepository adopcionRepository;

    @Autowired
    private AnimalRepository animalRepository;

    // @Autowired
    // private TiendaRepository tiendaRepository;

    @Autowired
    private EmailService emailService;

    public AdopcionModel save(AdopcionModel adopcion) {
        return adopcionRepository.save(adopcion);
    }
// - - - - -  GUARDAR LOS POSIBLES ADOPTANTES - - - - -
    public AdopcionModel guardarAdopcion(AdopcionModel adopcion) {

// - - - - - Guardado de las RELACIONES - - - - -        
        Optional<AnimalModel> animal = animalRepository.findById(adopcion.getAnimal().getIdAnimal());
        // Relacion con tienda; esta buena solo hace falta descomentar la linea de abajo
        //Optional<TiendaModel> tienda = tiendaRepository.findById(adopcion.getTienda().getId());

        if (animal.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal no encontrado");
        }

// - - - - - Validación: máximo 3 postulaciones por animal - - - - -
        long totalPostulaciones = adopcionRepository.countByAnimal_IdAnimal(adopcion.getAnimal().getIdAnimal());

        if (totalPostulaciones >= 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este animal ya tiene el máximo de 3 postulaciones, por favor selecciona otro animal");
        }

// - - - - - Informacion para enviar correo al posible adoptante - - - - -
        String nombreAnimal = animal.get().getNombreAnimal();
        AdopcionModel nuevaAdopcion = adopcionRepository.save(adopcion);
        emailService.enviarCorreoAgradecimiento(
            adopcion.getCorreo(),
            nombreAnimal
        );

// - - - - - Informacion para enviar el correo a la tienda: - - - - -
        String correoTienda = animal.get().getCorreoTienda();
        //String baseUrl = "http://localhost:8080/assets/";
        //String fotoAnimal = baseUrl + animal.get().getImagenAnimal();
        String nombreAdoptante = adopcion.getNombre();
        String telefonoAdoptante = adopcion.getTelefono();
        String emailAdoptante = adopcion.getCorreo();
        if (correoTienda != null && !correoTienda.isEmpty()) {
            emailService.enviarCorreoATienda(
                correoTienda,
                nombreAnimal,
                //fotoAnimal,
                nombreAdoptante,
                telefonoAdoptante,
                emailAdoptante
            );
        }

        return nuevaAdopcion;
    }


    public List<AdopcionModel> findAll() {
        return adopcionRepository.findAll();
    }

    public Optional<AdopcionModel> findById(Integer id) {
        return adopcionRepository.findById(id);
    }

    public void deleteById(Integer id) {
        adopcionRepository.deleteById(id);
    }
}
