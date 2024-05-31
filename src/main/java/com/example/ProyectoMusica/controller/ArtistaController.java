package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Artista;
import com.example.ProyectoMusica.service.ServicioArtista;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.coyote.http11.Constants.a;

@Controller
@RequestMapping("/artista")
public class ArtistaController {

    ServicioArtista servicioArtista = new ServicioArtista();

    @GetMapping("/li")
    public String vista(Model model) {
        String valorfinal = "/musicmatch/artista/listaA";
        try {
            model.addAttribute("artistas", servicioArtista.listarTodosLosArtistas());
            model.addAttribute("altaArtista", new Artista());

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
    @GetMapping("/altaAF")
    public String greetingForm(Model model) {
        model.addAttribute("altaArtista", new Artista());
        return "./musicmatch/artista/li";
    }
    @PostMapping("/altaA")
    public String greetingSubmit(@ModelAttribute Artista artista, Model model) throws SQLException {
        String valorfinal="redirect:/artista/li";
        try {
            servicioArtista.altaArtista(artista);
            model.addAttribute("altaArtistas", servicioArtista.listarTodosLosArtistas());
        } catch (SQLException ex) {
            valorfinal="error";
        }
        return valorfinal;
    }
    @GetMapping("/eliminarA")
    public String SubmitB (@RequestParam("codArtista") int id, Model model){
        String valorfinal="redirect:/artista/li";
        try {
            servicioArtista.eliminar(id);
            model.addAttribute("artistas", servicioArtista.listarTodosLosArtistas());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
    @GetMapping("/modificarA")
    public String modificar(@RequestParam ("codArtista") int id,Model model){
        String valorfinal="/musicmatch/artista/modificarA";
        try {
            model.addAttribute("artista", servicioArtista.getUnicoArtista(id));
        } catch (SQLException ex) {
        }
        return valorfinal;
    }

    @PostMapping("/modificarA")
    public String modificarBBDD (@ModelAttribute Artista artista, Model model){
        String valorfinal="redirect:/artista/li";
        try {
            servicioArtista.modificar(artista);
            model.addAttribute("artistas",servicioArtista.listarTodosLosArtistas());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
}
