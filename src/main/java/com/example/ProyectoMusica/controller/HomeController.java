package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.service.ServicioCancion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/tablero")
public class HomeController {

    private final ServicioCancion servicioCancion;

    public HomeController(ServicioCancion servicioCancion) {
        this.servicioCancion = servicioCancion;
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
