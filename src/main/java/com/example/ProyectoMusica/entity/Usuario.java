package com.example.ProyectoMusica.entity;


public class Usuario {
    private int idUsuario;
    public String nombreUsuario;
    private String correoElectronico;
    private String clave;
    public String lista;

    public Usuario(String nombreUsuario, String correoElectronico, String clave, String lista) {
        this.nombreUsuario  =nombreUsuario;
        this.correoElectronico=correoElectronico;
        this.clave = clave;
        this.lista=lista;
    }

    public Usuario(int idUsuario, String nombreUsuario, String correoElectronico, String clave, String lista) {
        this.idUsuario=idUsuario;
        this.nombreUsuario=nombreUsuario;
        this.correoElectronico=correoElectronico;
        this.clave=clave;
        this.lista=lista;
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

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }
    //asdfsdfsdf
    @Override
    public String toString() {

        return String.format("%-4d %-20s %-20s %-15s %-25s",
                idUsuario,
                nombreUsuario,
                correoElectronico,
                clave,
                lista);
    }
}