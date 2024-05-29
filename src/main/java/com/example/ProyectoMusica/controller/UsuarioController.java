package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Usuario;
import com.example.ProyectoMusica.service.ServicioUsuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/musicmatch")
public class UsuarioController {
    private final ServicioUsuario servicioUsuario = new ServicioUsuario();

    @GetMapping("/usuario")
    public String listarUsuarios(Model model) throws SQLException {
        String vista = "./musicmatch/usuario";
        List<Usuario> usuarios = servicioUsuario.listarTodosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return vista;
    }

    @GetMapping("/login")
    public String iniciarSesion(String email, String clave, Model model) {
        try {
            Usuario usuario = servicioUsuario.iniciarSesion(email, clave);
            if (usuario != null) {
                return "redirect:/musicmatch/home";
            } else {
                model.addAttribute("error", "Credenciales inválidas. Inténtelo de nuevo.");
                return "./musicmatch/Login/login";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }

    @GetMapping("/registro")
    public String verPaginaRegistro(String nombre, String email, String clave, Model model) {
        try {
            servicioUsuario.registrarUsuario(nombre, email, clave);
            return "redirect:/musicmatch/home";
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute("error", "Error al registrar el usuario. Inténtelo de nuevo.");
            return "./musicmatch/Login/register";
        }
    }

    @PostMapping("/registro")
    public String registrarUsuario(String nombre, String email, String clave, Model model) {
        try {
            servicioUsuario.registrarUsuario(nombre, email, clave);
            return "redirect:/musicmatch/home";
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute("error", "Error al registrar el usuario. Inténtelo de nuevo.");
            return "./musicmatch/Login/register";
        }
    }
}
