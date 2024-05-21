package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.service.ServicioArtista;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
@RequestMapping("/musicmatch/home")
public class ArtistaController {
    ServicioArtista artista = new ServicioArtista();

    @GetMapping("/")
    public String vista(Model model) {
        String valorfinal = "./musicmatch/Artista";
        try {
            model.addAttribute("artistas", artista.listarArtista());
            model.addAttribute("Artista", artista.getArtista(1));

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
}
