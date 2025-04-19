 /** 
 * @author Alejandro Perez CC 8.029.742
 * @author Julian David Giraldo Murillo CC 1.007.240.094
 * @author Andres Escobar Vasquez CC 1.038.096.962
 * @author Jorge Andres Restrepo Cataño CC 98.648.720
 **/
 
package com.pruebas.service;

import com.pruebas.model.TiendaModel;
import com.pruebas.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    public List<TiendaModel> obtenerTodas() {
        return tiendaRepository.findAll();
    }

    public Optional<TiendaModel> obtenerPorId(Integer id) {
        return tiendaRepository.findById(id);
    }

    public TiendaModel guardar(TiendaModel tienda) {
        return tiendaRepository.save(tienda);
    }

    public boolean eliminar(Integer id) {
        if (tiendaRepository.existsById(id)) {
            tiendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}