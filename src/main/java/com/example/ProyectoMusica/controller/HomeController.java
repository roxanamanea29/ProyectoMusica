package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.service.ServicioCancion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Roxana
 * @date 18/04/2024
 */
//ruta url http://localhost:8080/tablero
@Controller
@RequestMapping("/tablero")
public class HomeController {
    private final ServicioCancion servicioCancion;

    public HomeController(ServicioCancion servicioCancion) {
        this.servicioCancion = servicioCancion;
    }

    @GetMapping("/li")
    public String someMethod() {
        return "./musicmatch/listar";
    }
        @GetMapping("/")
        public String home(Model model) {
            String valorfinal = "/musicmatch/tableroU";
            try {
                model.addAttribute("canciones", servicioCancion.listar());
                //model.addAttribute("listasDeReproduccion", servicioListaReproduccion.listarAllListaReproduccion());
            } catch (Exception ex) {
                Logger.getLogger(com.example.ProyectoMusica.controller.HomeController.class.getName()).log(Level.SEVERE, null, ex);
                valorfinal = "error";
            }
            return valorfinal;
        }
}


