
 
package com.pruebas.service;

import com.pruebas.model.UsuarioModel;
import com.pruebas.model.DatosPersonalesModel;
import com.pruebas.repository.UsuarioRepository;
import com.pruebas.repository.DatosPersonalesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // RELACION
    @Autowired
    private DatosPersonalesRepository datosPersonalesRepository;

    @Autowired
    // 🔹 Agregamos BCrypt para cifrar y comparar contraseñas.
    private BCryptPasswordEncoder passwordEncoder; 

     public List<UsuarioModel> obtenerTodos() {
         return usuarioRepository.findAll();
     }

     public Optional<UsuarioModel> obtenerPorId(int id) {
         return usuarioRepository.findById(id);
     }

     public Optional<UsuarioModel> obtenerPorEmail(String email) {
         return usuarioRepository.findByEmail(email);
     }

    // GUARDAR UN USUARIO
    @Transactional
     public UsuarioModel guardar(UsuarioModel usuario) {
         // 🔹 Cambio: Cifrar la contraseña antes de guardarla en la BD. // Encriptar contraseña
         usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
         return usuarioRepository.save(usuario);
         
     }

    public Optional<UsuarioModel> autenticarUsuario(String email, String password) {
        Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent() && passwordEncoder.matches(password, usuario.get().getPassword())) {
            return usuario; // 🔹 Si la contraseña es correcta, retorna el usuario.
        }
        return Optional.empty(); // 🔹 Si la contraseña es incorrecta, retorna vacío.
    }



    public boolean eliminar(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }


    





}