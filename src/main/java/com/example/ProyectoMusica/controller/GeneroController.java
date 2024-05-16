package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.service.ServicioGenero;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/musicmatch/casa")
public class GeneroController {
    ServicioGenero genero = new ServicioGenero();

    @GetMapping("/")
    public String vista(Model model) {
        String valorfinal = "./musicmatch/Genero";
        try {
            model.addAttribute("generos", genero.listarTodosGeneros());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
}
