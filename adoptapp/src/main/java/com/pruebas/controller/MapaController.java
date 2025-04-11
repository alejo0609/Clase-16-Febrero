// src/main/java/com/pruebas/controller/MapaController.java
package com.pruebas.controller;

import com.pruebas.dto.LineaDTO;
import com.pruebas.model.TiendaModel;
import com.pruebas.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/mapa")
@CrossOrigin(origins = "*")
public class MapaController {

    @Autowired
    private TiendaRepository tiendaRepository;

    @GetMapping("/lineas")
    public List<LineaDTO> obtenerLineas(
        @RequestParam double userLat,
        @RequestParam double userLon
    ) {
        List<TiendaModel> tiendas = tiendaRepository.findAll();

        List<LineaDTO> lineas = new ArrayList<>();

        for (TiendaModel tienda : tiendas) {
            if (tienda.getLatitud() != null && tienda.getLongitud() != null) {
                lineas.add(new LineaDTO(
                        userLat,
                        userLon,
                        tienda.getLatitud(),
                        tienda.getLongitud()
                ));
            }
        }

        return lineas;
    }
}
