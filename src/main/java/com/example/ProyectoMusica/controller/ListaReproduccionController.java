package com.example.ProyectoMusica.controller;


import com.example.ProyectoMusica.entity.ListaReproduccion;
import com.example.ProyectoMusica.service.ServicioListaReproduccion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
@Controller
@RequestMapping("/musicmatch/lista")
public class ListaReproduccionController {
    ServicioListaReproduccion serListaReproduccion = new ServicioListaReproduccion();

    @GetMapping("/")
    public String vista(Model model) {
        String valorfinal = "./musicmatch/listas";
        try {
            model.addAttribute("listas", serListaReproduccion.listarTodasListasReproduccion());
            model.addAttribute("altaListaReproduccion", new ListaReproduccion());

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/eliminar")
    public String SubmitB (@RequestParam("codListaReproduccion") int id, Model model){
        String valorfinal="redirect:/musicmatch/lista";
        try {
            serListaReproduccion.eliminar(id);
            model.addAttribute("listas", serListaReproduccion.listarTodasListasReproduccion());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
    @GetMapping("/modificar")
    public String modificar(@RequestParam ("codListaReproduccion") int id,Model model){
        String valorfinal="./musicmatch/ModificarListaReproduccion";
        try {
            model.addAttribute("listareproduccion", serListaReproduccion.getUnicaListaReproduccion(id));
        } catch (SQLException ex) {
        }
        return valorfinal;
    }

    @PostMapping("/modificar")
    public String modificarBBDD (@ModelAttribute ListaReproduccion listaReproduccion, Model model){
        String valorfinal="redirect:/musicmatch/lista";
        try {
            serListaReproduccion.modificar(listaReproduccion);
            model.addAttribute("listas",serListaReproduccion.listarTodasListasReproduccion());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }

}
