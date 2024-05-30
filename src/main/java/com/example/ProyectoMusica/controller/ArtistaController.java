package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Artista;
import com.example.ProyectoMusica.service.ServicioArtista;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/artista")
public class ArtistaController {

   private ServicioArtista servicioArtista = new ServicioArtista();

    @GetMapping("/listaA")
    public String listarArtista(Model model) {
        String valorfinal= "/musicmatch/artista/listaA";
        try {
            List<Artista> artistas = servicioArtista.listarArtista();
            model.addAttribute("artistas", artistas);
        } catch (SQLException e) {
            Logger.getLogger(ArtistaController.class.getName()).log(Level.SEVERE, null, e);
            return "/musicmatch/cancion/error";
        }
        return valorfinal;
    }
    @GetMapping("/altaA")
    public String mostrarFormularioAlta(Model model) {
        String valorfinal = "/musicmatch/artista/altaA";
        model.addAttribute("artista",new Artista());
        return valorfinal;
    }

    @PostMapping("/altaA")
    public String guardarArtista(@ModelAttribute Artista artista,Model model) {
        String valorfinal = "/musicmatch/artista/listaA";

        try {
            servicioArtista.alta(artista);
            try {
                model.addAttribute("artistas", servicioArtista.listarArtista());
            } catch (SQLException ex) {
                Logger.getLogger(CancionController.class.getName()).log(Level.SEVERE, null, ex);
                valorfinal = "error";
            }
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/modificarA")
    public String modificar(@RequestParam("codartista") int idA, Model model) {
       String valorfinal = "/musicmatch/artista/modificarA";
        try {
            Artista artista = servicioArtista.getArtista(idA);
            model.addAttribute("artista", artista);
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaController.class.getName()).log(Level.SEVERE, null, ex);
            return "/musicmatch/cancion/error";
        }
        return valorfinal;
    }

    @PostMapping("/modificarA")
    public String modificarArtista(@ModelAttribute Artista artista, Model model) {
        String valorfinal = "/musicmatch/artista/listaA";
        try {
            servicioArtista.modificarArtista(artista);
            model.addAttribute("artistas", servicioArtista.listarArtista());
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
}
