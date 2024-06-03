package com.example.ProyectoMusica.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//http://localhost:8080/musicmatch/admin/
@Controller
@RequestMapping("/musicmatch/admin")
public class AdminController {
    @GetMapping("/")
    public String verPaginaInicioAdmin() {
        return "/musicmatch/admin";
    }
}
