package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.service.ServicioUsuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;
@Controller
@RequestMapping("/musicmatch/home")
public class UsuarioController {
    ServicioUsuario usuario = new ServicioUsuario();
    
    @GetMapping("/")
    public String vista(Model model) {
        String valorfinal = "./musicmatch/Usuario";
        try {
            model.addAttribute("usuarios", usuario.listarTodosUsuarios());
            model.addAttribute("unicoUsuario", usuario.getUnicoUsuario(1));

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
}
