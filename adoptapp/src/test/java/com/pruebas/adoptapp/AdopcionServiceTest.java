package com.pruebas.service;

import com.pruebas.model.AdopcionModel;
import com.pruebas.model.AnimalModel;
import com.pruebas.repository.AdopcionRepository;
import com.pruebas.repository.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdopcionServiceTest {

    @Mock
    private AdopcionRepository adopcionRepository;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AdopcionService adopcionService;

    @Test
    public void testGuardarAdopcionYEnvioCorreo() {
        // Crear y configurar el objeto AnimalModel
        AnimalModel animal = new AnimalModel();
        animal.setIdAnimal(1);
        animal.setNombreAnimal("Firulais");

        // Crear el objeto AdopcionModel y configurar el correo
        AdopcionModel adopcion = new AdopcionModel();
        adopcion.setCorreo("prueba@example.com");
        adopcion.setAnimal(animal);  // Establecer el animal en la adopción

        // Configuración de los mocks
        when(animalRepository.findById(1)).thenReturn(Optional.of(animal));
        when(adopcionRepository.save(adopcion)).thenReturn(adopcion);

        // Ejecutar el método
        AdopcionModel resultado = adopcionService.guardarAdopcion(adopcion);

        // Verificar que los métodos fueron llamados
        verify(adopcionRepository).save(adopcion);
        verify(emailService).enviarCorreoAgradecimiento("prueba@example.com", "Firulais");

        // Verificar el resultado
        assertEquals(adopcion, resultado);
    }

    @Test
    public void testGuardarAdopcionCuandoAnimalNoExisteLanzaExcepcion() {
        // Crear el objeto de adopción
        AdopcionModel adopcion = new AdopcionModel();

        // Crear y configurar un animal con ID inexistente
        AnimalModel animal = new AnimalModel();
        animal.setIdAnimal(999); // ID que no existe
        adopcion.setAnimal(animal);

        // Simular que no se encuentra el animal
        when(animalRepository.findById(999)).thenReturn(Optional.empty());

        // Ejecutar y verificar la excepción
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            adopcionService.guardarAdopcion(adopcion);
        });

        assertEquals("404 NOT_FOUND \"Animal no encontrado\"", exception.getMessage());
    }


}
