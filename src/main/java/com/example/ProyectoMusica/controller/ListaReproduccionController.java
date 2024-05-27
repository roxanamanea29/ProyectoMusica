package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.ListaReproduccion;
import com.example.ProyectoMusica.service.ServicioCancionListaReproduccion;
import com.example.ProyectoMusica.service.ServicioListaReproduccion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Adrian
 * @date 17/05/2024
 */
// ❗️ruta url http://localhost:8080/lista/
@Controller
@RequestMapping("/musicmatch")


public class ListaReproduccionController {
    ServicioListaReproduccion servicioLista = new ServicioListaReproduccion();
    private ListaReproduccion listaReproduccion;

    ServicioCancionListaReproduccion servicio = new ServicioCancionListaReproduccion();


    @GetMapping("/lista")
    public String crud(Model model) {
        String valorfinal = "./musicmatch/lista";

        try {
            //model.addAttribute("canciones", servicioCancionListaReproduccion.listarAllCanciones());
           // model.addAttribute("listasDeReproduccion", servicioLista.listarAllListaReproduccion(listaReproduccion));
            List<ListaReproduccion> listaDeReproducciones = (List<ListaReproduccion>) servicioLista.listarAllListaReproduccion();



        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
}

