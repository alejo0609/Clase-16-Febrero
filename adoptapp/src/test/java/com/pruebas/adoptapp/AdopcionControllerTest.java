// package com.pruebas.adoptapp;

// import com.pruebas.controller.AdopcionController;
// import com.pruebas.model.AdopcionModel;
// import com.pruebas.model.AnimalModel;
// import com.pruebas.model.TiendaModel;
// import com.pruebas.service.AdopcionService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.http.ResponseEntity;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// public class AdopcionControllerTest {

//     private AdopcionController adopcionController;
//     private AdopcionService adopcionService;

//     @BeforeEach
//     public void setup() {
//         adopcionService = mock(AdopcionService.class);
//         adopcionController = new AdopcionController();
//         // Inyección manual del mock (ya que usas @Autowired)
//         var field = AdopcionController.class.getDeclaredFields()[0];
//         field.setAccessible(true);
//         try {
//             field.set(adopcionController, adopcionService);
//         } catch (Exception e) {
//             throw new RuntimeException(e);
//         }
//     }

//     @Test
//     public void testRegistrarAdopcion() {
//         AdopcionModel adopcion = new AdopcionModel();
//         adopcion.setNombre("Juan");
//         adopcion.setDni("123456789");
//         adopcion.setDireccion("Calle 123");
//         adopcion.setCiudad("Medellín");
//         adopcion.setTelefono("3012345678");
//         adopcion.setCorreo("juan@example.com");
//         adopcion.setOcupacion("Ingeniero");
//         adopcion.setTipoVivienda("Apartamento");
//         adopcion.setMotivoAdopcion("Quiero darle un hogar a un perrito");
//         adopcion.setAprobado(false);

//         AnimalModel animal = new AnimalModel();
//         animal.setIdAnimal(1);
//         adopcion.setAnimal(animal);

//         TiendaModel tienda = new TiendaModel();
//         tienda.setId(22L);
//         adopcion.setTienda(tienda);


//         //doNothing().when(adopcionService).guardarAdopcion(any(AdopcionModel.class));
//         when(adopcionService.guardarAdopcion(any(AdopcionModel.class))).thenReturn(adopcion);


//         ResponseEntity<String> response = adopcionController.registrarAdopcion(adopcion);

//         assertEquals(200, response.getStatusCodeValue());
//         assertEquals("Formulario de adopción registrado con éxito.", response.getBody());
//     }
// }
