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
    public String listarUsuarios(Model model) {
        model.addAttribute("altaUsuario", new Usuario());
        try {
            List<Usuario> usuarios = servicioUsuario.listarTodosUsuarios();
            model.addAttribute("usuarios", usuarios);
            return "musicmatch/usuario/listarU";
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }

    @GetMapping("/registro")
    public String verPaginaRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "musicmatch/usuario/registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            servicioUsuario.registrarUsuario(usuario.getNombreUsuario(), usuario.getCorreoElectronico(), usuario.getClave());
            return "redirect:/musicmatch/home";
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute("error", "Error al registrar el usuario. Inténtelo de nuevo.");
            return "musicmatch/Login/register";
        }
    }

    @GetMapping("/alta")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "musicmatch/usuario/altaU";
    }

    @PostMapping("/alta")
    public String procesarFormularioAlta(@ModelAttribute Usuario usuario, Model model) {
        try {
            servicioUsuario.altaUsuario(usuario);
            return "redirect:/usuario/listarU";
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }

    @GetMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("codUsuario") int id, Model model) {
        try {
            servicioUsuario.eliminar(id);
            return "redirect:/usuario/listarU";
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }

    @GetMapping("/modificar")
    public String mostrarFormularioModificar(@RequestParam("codUsuario") int id, Model model) {
        try {
            Usuario usuario = servicioUsuario.getUnicoUsuario(id);
            model.addAttribute("usuario", usuario);
            return "musicmatch/usuario/modificarU";
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }

    @PostMapping("/modificar")
    public String procesarFormularioModificar(@ModelAttribute Usuario usuario, Model model) {
        try {
            servicioUsuario.modificar(usuario);
            return "redirect:/usuario/listarU";
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
}

