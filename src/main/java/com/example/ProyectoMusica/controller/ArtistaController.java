package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Artista;
import com.example.ProyectoMusica.service.ServicioArtista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/musicmatch")
public class ArtistaController {

    @Autowired
    private ServicioArtista servicioArtista;

    @GetMapping("/artista")
    public String listarArtista(Model model) {
        try {
            List<Artista> artistas = servicioArtista.listarArtista();
            model.addAttribute("artistas", artistas);
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "musicmatch/Artista";
    }

    @GetMapping("/artista/modificar")
    public String modificar(@RequestParam("codartista") int idArtista, Model model) {
        try {
            Artista artista = servicioArtista.getArtista(idArtista);
            model.addAttribute("artista", artista);
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        return "musicmatch/modificarArtista";
    }

    @PostMapping("/artista/modificar")
    public String modificarBBDD(@ModelAttribute Artista artista, Model model) {
        try {
            servicioArtista.modificarArtista(artista);
            List<Artista> artistas = servicioArtista.listarArtista();
            model.addAttribute("artistas", artistas);
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        return "redirect:/musicmatch/artista";
    }
}