package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Genero;
import com.example.ProyectoMusica.service.ServicioCancion;
import com.example.ProyectoMusica.service.ServicioGenero;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class GeneroController {
    ServicioGenero serGenero = new ServicioGenero();

    @GetMapping("/genero")
    public String vista(Model model) {
        String valorfinal = "./musicmatch/genero/altaG";
        try {
            model.addAttribute("generos", serGenero.listarTodosGeneros());
            model.addAttribute("altaGenero", new Genero());
            //tengo que cambiar el "canciones"
            //model.addAttribute("canciones", serGenero.listarCancionGenero());
            model.addAttribute("imageUrls",serGenero.imagenGenero());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
    @GetMapping("/alta")
    public String greetingForm(Model model) {
        model.addAttribute("altaGenero", new Genero());
        return "./musicmatch/genero/altaG";
    }
    @PostMapping("/alta")
    public String greetingSubmit(@ModelAttribute Genero genero, Model model) throws SQLException {
        String valorfinal="redirect:/admin/genero";
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
        String valorfinal="redirect:/admin/genero";
        try {
            serGenero.eliminar(id);
            model.addAttribute("generos", serGenero.listarTodosGeneros());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
    @GetMapping("/modificar")
    public String modificar(@RequestParam ("codGenero") int id,Model model){
        String valorfinal="./musicmatch/genero/ModificarGenero";
        try {
            model.addAttribute("genero", serGenero.getUnicoGenero(id));
        } catch (SQLException ex) {
        }
        return valorfinal;
    }

    @PostMapping("/modificar")
    public String modificarBBDD (@ModelAttribute Genero genero, Model model){
        String valorfinal="redirect:/admin/genero";
        try {
            serGenero.modificar(genero);
            model.addAttribute("generos",serGenero.listarTodosGeneros());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
    @GetMapping("/lista")
    public String lista(@RequestParam ("codGenero") int id,Model model) {
        String valorfinal = "./musicmatch/genero/listaCancion";
        try {
            //tengo que cambiar el "canciones"
            model.addAttribute("listas", serGenero.listarCancionGenero(id));
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

}
