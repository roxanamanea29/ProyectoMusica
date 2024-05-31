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
@RequestMapping("/musicmatch/listareproduccion/")
public class ListaReproduccionController {
    ServicioListaReproduccion serListaReproduccion = new ServicioListaReproduccion();

    @GetMapping("/")
    public String vista(Model model) {
        String valorfinal = "/musicmatch/listareproduccion";
        try {
            model.addAttribute("acciones", serListaReproduccion.listarTodasListasReproduccion());
            model.addAttribute("altaListaReproduccion", new ListaReproduccion());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @PostMapping("/alta")
    public String alta(@ModelAttribute("altaListaReproduccion") ListaReproduccion listaReproduccion, Model model) {
        String valorfinal = "redirect:/musicmatch/listareproduccion/"; // Redirige a la página principal después de agregar
        try {
            serListaReproduccion.agregar(listaReproduccion); // Llama al método de servicio para agregar la lista de reproducción
            model.addAttribute("acciones", serListaReproduccion.listarTodasListasReproduccion()); // Actualiza la lista de reproducción en el modelo
        } catch (SQLException ex) {
            valorfinal = "error"; // Manejo de errores
        }
        return valorfinal;
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam("codListaReproduccion") int id, Model model) {
        String valorfinal = "redirect:/musicmatch/listareproduccion/";
        try {
            serListaReproduccion.eliminar(id);
            model.addAttribute("acciones", serListaReproduccion.listarTodasListasReproduccion());
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam("codListaReproduccion") int id, Model model) {
        String valorfinal = "/musicmatch/ModificarListaReproduccion";
        try {
            model.addAttribute("listareproduccion", serListaReproduccion.getUnicaListaReproduccion(id));
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }

    @PostMapping("/modificar")
    public String modificarBBDD(@ModelAttribute ListaReproduccion listaReproduccion, Model model) {
        String valorfinal = "redirect:/musicmatch/listareproduccion/";
        try {
            serListaReproduccion.modificar(listaReproduccion);
            model.addAttribute("acciones", serListaReproduccion.listarTodasListasReproduccion());
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }
}
