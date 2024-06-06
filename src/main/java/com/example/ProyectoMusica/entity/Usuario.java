package com.example.ProyectoMusica.entity;


public class Usuario {
    private int idUsuario;
    public String nombreUsuario;
    private String correoElectronico;
    private String clave;

    public Usuario(String nombreUsuario, String correoElectronico, String clave) {
        this.nombreUsuario  =nombreUsuario;
        this.correoElectronico=correoElectronico;
        this.clave = clave;
    }

    public Usuario(int idUsuario, String nombreUsuario, String correoElectronico, String clave) {
        this.idUsuario=idUsuario;
        this.nombreUsuario=nombreUsuario;
        this.correoElectronico=correoElectronico;
        this.clave=clave;
    }

    public Usuario(int idUsuario, String nombreUsuario) {
    }

    public Usuario() {

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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {

        return String.format("%-4d %-20s %-20s %-15s",
                idUsuario,
                nombreUsuario,
                correoElectronico,
                clave);
    }
}