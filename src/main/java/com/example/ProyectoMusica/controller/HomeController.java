package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.service.ServicioCancion;
import com.example.ProyectoMusica.service.ServicioListaReproduccion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/tablero")
public class HomeController {
    ServicioCancion servicioCancion = new ServicioCancion();
    ServicioListaReproduccion servicioLista = new ServicioListaReproduccion();

    @GetMapping("")
    public String someMethod() {
        return "./musicmatch/tableroU";
    }

    @GetMapping("/")
    public String home(Model model) {
        String valorfinal = "tableroU";
        try {
            model.addAttribute("canciones", servicioCancion.listarAllCanciones());
            model.addAttribute("listasDeReproduccion", servicioLista.listarAllListaReproduccion());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
}
