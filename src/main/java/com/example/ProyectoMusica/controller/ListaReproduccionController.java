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

    @GetMapping("/listaR/{id}")
    public String vista (@PathVariable int id, Model model) {
        String valorfinal = "/musicmatch/listareproduccion";
        try {
            model.addAttribute("listaR", id);
            model.addAttribute("canciones", serListaReproduccion.listarCancionesListasReproduccion(id));
           // model.addAttribute("altaListaReproduccion", new ListaReproduccion());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @PostMapping("/alta")
    public String alta(@ModelAttribute("altaListaReproduccion") ListaReproduccion listaReproduccion, Model model) {
        String valorfinal = "redirect:/musicmatch/listareproduccion/";
        try {
            serListaReproduccion.agregar(listaReproduccion);
            model.addAttribute("cancionesR", serListaReproduccion.listarTodasListasReproduccion());
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id,@RequestParam("codCancion") int idCancionLista, Model model) {
        String valorfinal = "redirect:/musicmatch/listareproduccion";
        try {
            serListaReproduccion.eliminar(idCancionLista);
            model.addAttribute("canciones", serListaReproduccion.listarCancionesListasReproduccion(id));
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }

   /* @GetMapping("/modificar")
    public String modificar(@RequestParam ("codListaReproduccion") int id,Model model){
        String valorfinal="./musicmatch/listareproduccion/ModificarLR";
        try {
            model.addAttribute("listareproduccion", serListaReproduccion.getUnicaListaReproduccion(id));
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
*/
    @PostMapping("/modificar")
    public String modificarBBDD (@ModelAttribute ListaReproduccion listaReproduccion, Model model){
        String valorfinal="redirect:/musicmatch/listareproduccion";
        try {
            serListaReproduccion.modificar(listaReproduccion);
            model.addAttribute("listasreproduccion",serListaReproduccion.listarTodasListasReproduccion());
        } catch (SQLException ex) {
        }
        return valorfinal;
    }
}
