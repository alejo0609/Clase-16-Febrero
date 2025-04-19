 /** 
 * @author Alejandro Perez CC 8.029.742
 * @author Julian David Giraldo Murillo CC 1.007.240.094
 * @author Andres Escobar Vasquez CC 1.038.096.962
 * @author Jorge Andres Restrepo Cataño CC 98.648.720
 **/
 
package com.pruebas.controller;

import com.pruebas.model.TiendaModel;
import com.pruebas.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @GetMapping
    public List<TiendaModel> obtenerTodas() {
        return tiendaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Optional<TiendaModel> obtenerPorId(@PathVariable Integer id) {
        return tiendaService.obtenerPorId(id);
    }

    @PostMapping
    public TiendaModel guardar(@RequestBody TiendaModel tienda) {
        return tiendaService.guardar(tienda);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        return tiendaService.eliminar(id) ? "Eliminado correctamente" : "No se encontró la tienda con ese ID";
    }
}