package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Genero;
import com.example.ProyectoMusica.service.ServicioCancion;

import com.example.ProyectoMusica.service.ServicioGenero;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/tablero")
public class HomeController {

    private final ServicioCancion servicioCancion;
    ServicioGenero serGenero = new ServicioGenero();


    public HomeController(ServicioCancion servicioCancion) {
        this.servicioCancion = servicioCancion;
    }


    @GetMapping("/li")
    public String someMethod() {
        return "./musicmatch/lista";
    }

    @GetMapping("/")
    public String home(Model model) {
        String valorfinal = "/musicmatch/tableroU";
        try {
            model.addAttribute("canciones", servicioCancion.listar());
            model.addAttribute("generos", serGenero.listarTodosGeneros());
            //model.addAttribute("listasDeReproduccion", servicioListaReproduccion.listarAllListaReproduccion());
        } catch (Exception ex) {
            Logger.getLogger(com.example.ProyectoMusica.controller.HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

}

    }
}
