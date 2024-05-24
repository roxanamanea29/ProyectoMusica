package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Artista;
import com.example.ProyectoMusica.service.ServicioArtista;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
@RequestMapping("/musicmatch")
public class ArtistaController {

    @GetMapping("/artista")
    public String perfilArtista() {
        String valorfinal = "/musicmatch/Artista";
        return valorfinal;
    }
}
