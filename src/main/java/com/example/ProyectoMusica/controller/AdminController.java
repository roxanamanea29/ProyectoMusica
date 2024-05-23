package com.example.ProyectoMusica.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/musicmatch/admin")
public class AdminController {
    @GetMapping("/")
    public String verPaginaInicioAdmin() {
        return "/musicmatch/admin";
    }
}
