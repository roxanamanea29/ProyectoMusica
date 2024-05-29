package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Genero;
import com.example.ProyectoMusica.service.ServicioGenero;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/musicmatch")
public class GeneroController {
    ServicioGenero serGenero = new ServicioGenero();

    @GetMapping("/")
    public String vista(Model model) {
        String valorfinal = "./musicmatch/Genero";
        try {
            model.addAttribute("generos", serGenero.listarTodosGeneros());
            model.addAttribute("altaGenero", new Genero());

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
    @GetMapping("/alta")
    public String greetingForm(Model model) {
        model.addAttribute("altaGenero", new Genero());
        return "./musicmatch/Genero";
    }
    @PostMapping("/alta")
    public String greetingSubmit(@ModelAttribute Genero genero, Model model) throws SQLException {
        String valorfinal="redirect:/musicmatch/";
        try {
            serGenero.altaGenero(genero);
            model.addAttribute("altaGeneros", serGenero.listarTodosGeneros());
        } catch (SQLException ex) {
            valorfinal="error";
        }
        return valorfinal;
    }
    @GetMapping("/eliminar")
    public String SubmitB (@RequestParam("codGenero") int id, Model model){
        String valorfinal="redirect:/musicmatch/";
        try {
            serGenero.eliminar(id);
            model.addAttribute("generos", serGenero.listarTodosGeneros());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
    @GetMapping("/modificar")
    public String modificar(@RequestParam ("codGenero") int id,Model model){
        String valorfinal="/musicmatch/";
        try {
            model.addAttribute("genero", serGenero.getUnicoGenero(id));
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
}
