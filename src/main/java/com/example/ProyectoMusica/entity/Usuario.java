package com.example.ProyectoMusica.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class Usuario {
    private int idUsuario;
    public String nombreUsuario;
    private String correoElectronico;
    private String contrasena;
    public String lista;

    public Usuario(String nombreUsuario, String correoElectronico, String contrasena, String lista) {
        this.nombreUsuario  =nombreUsuario;
        this.correoElectronico=correoElectronico;
        this.contrasena = contrasena;
        this.lista=lista;
    }

    public Usuario(int idUsuario, String nombreUsuario, String correoElectronico, String contrasena, String lista) {
        this.idUsuario=idUsuario;
        this.nombreUsuario=nombreUsuario;
        this.correoElectronico=correoElectronico;
        this.contrasena=contrasena;
        this.lista=lista;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {

        return String.format("%-4d %-20s %-20s %-15s %-25s",
                idUsuario,
                nombreUsuario,
                correoElectronico,
                contrasena,
                lista);
    }
}

