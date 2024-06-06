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

@RequestMapping("/listareproduccion")
public class ListaReproduccionController {
    ServicioListaReproduccion serListaReproduccion = new ServicioListaReproduccion();
    public int idListR;
    @GetMapping("/{id}")
    public String vista (@PathVariable int id, Model model) {
        String valorfinal = "/musicmatch/listareproduccion";
        try {
            idListR=id;
            model.addAttribute("listaR",id);
            model.addAttribute("canciones", serListaReproduccion.listarCancionesListasReproduccion(id));
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
    @GetMapping("/listaCR")
    public String vista (Model model) {
        String valorfinal = "./musicmatch/altaCLR";
        try {
            model.addAttribute("canciones", serListaReproduccion.listarCancionesTodasListasReproduccion());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/listaLR")
    public String vista2 (Model model) {
        String valorfinal = "./musicmatch/altaLR";
        try {
            model.addAttribute("listasR", serListaReproduccion.listarTodasListasReproduccion());
            model.addAttribute("agregarListaR", new ListaReproduccion());
            model.addAttribute("imageUrls",serListaReproduccion.imagenListaR());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

@GetMapping("/altaCR")
public String agregarCancionLR(@RequestParam("codCancion") int idCancion, Model model) {
    String valorfinal = "redirect:/listareproduccion/"+idListR;
    try {
        serListaReproduccion.agregarCancionLR(idCancion,idListR);
        model.addAttribute("canciones", serListaReproduccion.listarCancionesListasReproduccion(idListR));
    } catch (SQLException ex) {
        valorfinal = "error";
    }
    return valorfinal;
}
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam("codCancion") int idCancion, Model model) {
        String valorfinal = "redirect:/listareproduccion/"+idListR;
        try {
            serListaReproduccion.eliminar(idCancion);
            model.addAttribute("canciones", serListaReproduccion.listarCancionesListasReproduccion(idListR));
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/altaLR")
    public String greetingForm(Model model) {
        String valorfinal = "./musicmatch/altaLR";
        model.addAttribute("agregarListaR", new ListaReproduccion());
        return valorfinal;
    }
    @PostMapping("/altaLR")
    public String altaListaR(@ModelAttribute ListaReproduccion listaReproduccion, Model model) {
        String valorfinal = "redirect:/listareproduccion/listaLR";
        try {
            serListaReproduccion.agregarListaR(listaReproduccion);
            model.addAttribute("agregarListasR", serListaReproduccion.listarTodasListasReproduccion());
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/eliminarLR")
    public String eliminarLR(@RequestParam("codListaR") int idListR, Model model) {
        String valorfinal = "redirect:/listareproduccion/listaLR";
        try {
            serListaReproduccion.eliminarLR(idListR);
            model.addAttribute("agregarListasR", serListaReproduccion.listarTodasListasReproduccion());
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }
    @GetMapping("/modificarLR")
    public String modificarLR(@RequestParam ("codListaR") int id, Model model) {
        String valorfinal = "./musicmatch/modificarLR";
        try {
            model.addAttribute("modificarListaR", serListaReproduccion.getUnicaListaReproduccion(id));
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
    @PostMapping("/modificarLR")
    public String modificarBBDD (@ModelAttribute ListaReproduccion listaReproduccion, Model model){
        String valorfinal = "redirect:/listareproduccion/listaLR";
        try {
            serListaReproduccion.modificarLR(listaReproduccion);
            model.addAttribute("modificarListasR",serListaReproduccion.listarTodasListasReproduccion());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
}