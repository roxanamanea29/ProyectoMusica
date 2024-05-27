package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Cancion;
import com.example.ProyectoMusica.service.ServicioArtista;
import com.example.ProyectoMusica.service.ServicioCancion;
import com.example.ProyectoMusica.service.ServicioGenero;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/cancion")
public class CancionController {

    ServicioCancion servicioCancion = new ServicioCancion();


    @GetMapping("/lista")
    public String listarCanciones(Model model) {
        String valorfinal = "/musicmatch/cancion/listarC";
        try {
            model.addAttribute("canciones", servicioCancion.listar());
        } catch (SQLException ex) {
            Logger.getLogger(CancionController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "/musicmatch/cancion/error";
        }
        return valorfinal;
    }


    @GetMapping("/alta")
    public String mostrarFormularioAlta(Model model) {
        ServicioArtista servicioArtista = new ServicioArtista();
        ServicioGenero servicioGenero = new ServicioGenero();
        String valorfinal = "/musicmatch/cancion/altaC";
        try {
            model.addAttribute("cancion", new Cancion());
            model.addAttribute("artistas", servicioArtista.listarArtista());
            model.addAttribute("generos", servicioGenero.listarTodosGeneros());
        } catch (SQLException ex) {
            Logger.getLogger(CancionController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "/musicmatch/cancion/error";
        }
        return valorfinal;
    }

    @PostMapping("/alta")
    public String guardarCancion(@ModelAttribute Cancion cancion, @RequestParam("idArtista") int idArtista, @RequestParam("idGenero") int idGenero, Model model) {
        String valorfinal = "/musicmatch/cancion/listarC";
        cancion.setIdArtista(idArtista);
        cancion.setIdGenero(idGenero);
        try {
            servicioCancion.alta(cancion);
            try {
                model.addAttribute("canciones", servicioCancion.listar());
            } catch (SQLException ex) {
                Logger.getLogger(CancionController.class.getName()).log(Level.SEVERE, null, ex);
                valorfinal = "error";
            }
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/modificar")
    public String mostrarFormularioModificacion(@RequestParam("cod_cancion") int idC, Model model) {
        ServicioArtista servicioArtista = new ServicioArtista();
        ServicioGenero servicioGenero = new ServicioGenero();
        String valorfinal = "/musicmatch/cancion/modificarC";
        try {
            Cancion cancion = servicioCancion.buscar(idC);
            model.addAttribute("cancion", cancion);
            model.addAttribute("artistas", servicioArtista.listarArtista());
            model.addAttribute("generos", servicioGenero.listarTodosGeneros());
        } catch (SQLException ex) {
            Logger.getLogger(CancionController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @PostMapping("/modificar")
    public String modificarCancion(@ModelAttribute Cancion cancion, @RequestParam("idArtista") int idArtista, @RequestParam("idGenero") int idGenero, Model model) {
        String valorfinal = "/musicmatch/cancion/listarC";
        cancion.setIdArtista(idArtista);
        cancion.setIdGenero(idGenero);
        try {
            servicioCancion.modificarCancion(cancion);
            model.addAttribute("canciones", servicioCancion.listar());
        } catch (SQLException ex) {
            Logger.getLogger(CancionController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/eliminar")
    public String eliminarCancion(@RequestParam("cod_cancion") int idC, Model model) {
        String valorfinal = "/musicmatch/cancion/listarC";
        try {
            servicioCancion.eliminarCancion(idC);
            model.addAttribute("canciones", servicioCancion.listar());
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }
}