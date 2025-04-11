/**
 * Servicio para la gestión de tiendas.
 * 
 * @author Alejandro Perez CC 8.029.742
 * @author Julian David Giraldo Murillo CC 1.007.240.094
 * @author Andres Escobar Vasquez CC 1.038.096.962
 * @author Jorge Andres Restrepo Cataño CC 98.648.720
 */

package com.pruebas.service;

import com.pruebas.model.TiendaModel;
import com.pruebas.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pruebas.utils.GeoUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private GeoUtils geoUtils;

    /**
     * Obtiene todas las tiendas registradas.
     * 
     * @return Lista de tiendas.
     */
    public List<TiendaModel> obtenerTodas() {
        return tiendaRepository.findAll();
    }

    /**
     * Obtiene una tienda por su ID.
     * 
     * @param id ID de la tienda.
     * @return Tienda encontrada (opcional).
     */
    public Optional<TiendaModel> obtenerPorId(Long id) {
        return tiendaRepository.findById(id);
    }

    /**
     * Guarda una tienda con sus coordenadas geográficas si es posible.
     * 
     * @param tienda Objeto de tienda a guardar.
     * @return Tienda guardada.
     */
    public TiendaModel guardarTienda(TiendaModel tienda) {
        // Construir una dirección más completa y precisa para enviar a GeoUtils
        String direccionCompleta = tienda.getDireccion() + ", " + tienda.getBarrio() + ", " + tienda.getCiudad();

        double[] coords = geoUtils.obtenerCoordenadas(direccionCompleta);
        if (coords != null) {
            tienda.setLatitud(coords[0]);
            tienda.setLongitud(coords[1]);
        }

        return tiendaRepository.save(tienda);
    }


    /**
     * Elimina una tienda por su ID.
     * 
     * @param id ID de la tienda a eliminar.
     * @return true si se eliminó correctamente, false si no existía.
     */
    public boolean eliminar(Long id) {
        if (tiendaRepository.existsById(id)) {
            tiendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
