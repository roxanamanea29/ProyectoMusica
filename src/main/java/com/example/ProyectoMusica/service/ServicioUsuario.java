package com.example.ProyectoMusica.service;

import com.example.ProyectoMusica.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioUsuario {
    private List<Usuario> usuarios;

    public ServicioUsuario() {
        this.usuarios = new ArrayList<>();
        // Aquí podrías inicializar algunos usuarios de ejemplo
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarios;
    }

    public Usuario getUnicoUsuario(int id) {
        // Aquí podrías implementar la lógica para obtener un usuario por su ID
        return null;
    }

    // Otros métodos para manipular usuarios como guardar, actualizar, eliminar, etc.
}
