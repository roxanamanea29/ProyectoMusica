package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Usuario;
import com.example.ProyectoMusica.service.ServicioUsuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/musicmatch")
public class UsuarioController {
    ServicioUsuario servicioUsuario = new ServicioUsuario();

    @GetMapping("/usuario")
    public String listarUsuarios(Model model) {
        String vista = "./musicmatch/usuario";
        try {
            List<Usuario> usuarios = servicioUsuario.listarTodosUsuarios();
            model.addAttribute("usuarios", usuarios);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            vista = "error";
        }
        return vista;
    }
}
