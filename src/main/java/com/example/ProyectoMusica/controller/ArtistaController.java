package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Artista;
import com.example.ProyectoMusica.service.ServicioArtista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/musicmatch")
public class ArtistaController {

    @Autowired
    private ServicioArtista servicioArtista;

    @GetMapping("/artista")
    public String perfilArtista(Model model) {
        try {
            List<Artista> artistas = servicioArtista.listarArtista();
            model.addAttribute("artistas", artistas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "musicmatch/Artista";
    }

    @GetMapping("/artista/{id}")
    public String detalleArtista(@PathVariable int id, Model model) {
        try {
            Artista artista = servicioArtista.getArtista(id);
            model.addAttribute("artista", artista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "musicmatch/DetalleArtista";
    }
}