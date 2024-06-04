package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Usuario;
import com.example.ProyectoMusica.service.ServicioUsuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final ServicioUsuario servicioUsuario = new ServicioUsuario();

    @GetMapping("/listarU")
    public String listarUsuarios(Model model) throws SQLException {
        String vista = "./musicmatch/usuario/listarU";
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

    @GetMapping("/alta")
    public String greetingForm(Model model) {
        model.addAttribute("listarUsuario", new Usuario());
        return "./musicmatch/usuario/listarU";
    }

    @PostMapping("/alta")
    public String greetingSubmit(@ModelAttribute Usuario usuario, Model model) throws SQLException {
        String valorfinal="redirect:/admin/usuario";
        try {
            servicioUsuario.altaUsuario(usuario);
            model.addAttribute("listarUsuarios", servicioUsuario.listarTodosUsuarios());
        } catch (SQLException ex) {
            valorfinal="error";
        }
        return valorfinal;
    }

    @GetMapping("/eliminar")
    public String SubmitB (@RequestParam("codUsuario") int id, Model model){
        String valorfinal="redirect:/admin/usuario";
        try {
            servicioUsuario.eliminar(id);
            model.addAttribute("usuarios", servicioUsuario.listarTodosUsuarios());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam("codUsuario") int id, Model model) {
        String vista = "./musicmatch/usuario/ModificarUsuario";
        try {
            Usuario usuario = servicioUsuario.getUnicoUsuario(id);
            model.addAttribute("usuario", usuario);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        return vista;
    }


    @PostMapping("/modificar")
    public String modificarBBDD (@ModelAttribute Usuario usuario, Model model){
        String valorfinal="redirect:/admin/usuario";
        try {
            servicioUsuario.modificar(usuario);
            model.addAttribute("usuarios",servicioUsuario.listarTodosUsuarios());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
}