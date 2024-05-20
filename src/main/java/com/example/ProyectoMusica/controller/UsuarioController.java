package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Usuario;
import com.example.ProyectoMusica.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/musicmatch")
public class UsuarioController {
    private final ServicioUsuario servicioUsuario;

    @Autowired
    public UsuarioController(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @GetMapping("/usuario")
    public String vista(Model model) {
        String valorfinal = "./musicmatch/Usuario";
        try {
            model.addAttribute("usuarios", servicioUsuario.listarTodosUsuarios());
            model.addAttribute("unicoUsuario", servicioUsuario.getUnicoUsuario(1));

        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
}
