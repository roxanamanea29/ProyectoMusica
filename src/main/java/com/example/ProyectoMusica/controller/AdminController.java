package com.example.ProyectoMusica.controller;

import com.example.ProyectoMusica.entity.Admin;
import com.example.ProyectoMusica.service.ServicioAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/musicmatch")
public class AdminController {
    private final ServicioAdmin servicioAdmin = new ServicioAdmin();

    @GetMapping("/admin")
    public String listarAdmins(Model model) {
        try {
            List<Admin> admins = servicioAdmin.listarTodosAdmins();
            model.addAttribute("admins", admins);
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "musicmatch/admin";
    }

    @GetMapping("/admin/modificar")
    public String mostrarFormularioModificacion(@RequestParam("idAdmin") int idAdmin, Model model) {
        try {
            Admin admin = servicioAdmin.buscarAdmin(idAdmin);
            model.addAttribute("admin", admin);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        return "musicmatch/modificarAdmin";
    }

    @PostMapping("/admin/modificar")
    public String modificarAdmin(@ModelAttribute Admin admin, Model model) {
        try {
            servicioAdmin.modificarAdmin(admin.getIdAdmin(), admin.getNombreAdmin(), admin.getCorreoElectronico(), admin.getClave());
            List<Admin> admins = servicioAdmin.listarTodosAdmins();
            model.addAttribute("admins", admins);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        return "redirect:/musicmatch/admin";
    }

    @GetMapping("/admin/eliminar")
    public String eliminarAdmin(@RequestParam("idAdmin") int idAdmin, Model model) {
        try {
            servicioAdmin.eliminarAdmin(idAdmin);
            List<Admin> admins = servicioAdmin.listarTodosAdmins();
            model.addAttribute("admins", admins);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        return "redirect:/musicmatch/admin";
    }

    @PostMapping("/admin/registrar")
    public String registrarAdmin(@ModelAttribute Admin admin, Model model) {
        try {
            servicioAdmin.registrarAdmin(admin.getNombreAdmin(), admin.getCorreoElectronico(), admin.getClave());
            List<Admin> admins = servicioAdmin.listarTodosAdmins();
            model.addAttribute("admins", admins);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        return "redirect:/musicmatch/admin";
    }
}
