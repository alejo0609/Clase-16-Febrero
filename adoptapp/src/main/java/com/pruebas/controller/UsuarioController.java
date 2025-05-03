/**
 * @author Alejandro Perez CC 8.029.742
 * @author Julian David Giraldo Murillo CC 1.007.240.094
 * @author Andres Escobar Vasquez CC 1.038.096.962
 * @author Jorge Andres Restrepo Cataño CC 98.648.720
 */

package com.pruebas.controller;

import com.pruebas.model.UsuarioModel;
import com.pruebas.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioModel> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        Optional<UsuarioModel> usuario = usuarioService.obtenerPorId(id);
        return usuario.isPresent()
            ? ResponseEntity.ok(usuario)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\": \"Usuario no encontrado\"}");
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody UsuarioModel usuario) {
        UsuarioModel nuevoUsuario = usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioModel usuario) {
        Optional<UsuarioModel> usuarioEncontrado = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getPassword());
        if (usuarioEncontrado.isPresent()) {
            return ResponseEntity.ok("{\"mensaje\": \"Inicio de sesión exitoso\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"mensaje\": \"Credenciales incorrectas\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        return usuarioService.eliminar(id)
            ? ResponseEntity.ok("{\"mensaje\": \"Eliminado correctamente\"}")
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\": \"No se encontró el ID\"}");
    }
}
