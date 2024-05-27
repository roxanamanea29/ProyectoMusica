package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.ListaReproduccion;
import com.example.ProyectoMusica.service.ServicioListaReproduccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/musicmatch")
public class ListaReproduccionController {
    private final ServicioListaReproduccion servicioListaReproduccion;

    @Autowired
    public ListaReproduccionController(ServicioListaReproduccion servicioListaReproduccion) {
        this.servicioListaReproduccion = servicioListaReproduccion;
    }

    @GetMapping("/lista")
    public String listarTodasListasReproduccion(Model model) {
        model.addAttribute("listas", servicioListaReproduccion.listarTodasListasReproduccion());
        return "./musicmatch/lista";
    }

    @GetMapping("/lista/{id}")
    public ListaReproduccion obtenerListaReproduccionPorId(@PathVariable int id) {
        return servicioListaReproduccion.obtenerListaReproduccion(id);
    }

    // Otros métodos para manipular listas de reproducción como guardar, actualizar, eliminar, etc.
}
